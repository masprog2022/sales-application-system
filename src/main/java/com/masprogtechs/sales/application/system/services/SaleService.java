package com.masprogtechs.sales.application.system.services;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.masprogtechs.sales.application.system.domain.entities.*;
import com.masprogtechs.sales.application.system.domain.entities.dto.category.CategoryDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.sale.SaleRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.sale.SaleResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.saleItem.SaleItemRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import com.masprogtechs.sales.application.system.domain.repositories.SaleRepository;
import com.masprogtechs.sales.application.system.domain.repositories.StockRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.exception.AuthorizationException;
import com.masprogtechs.sales.application.system.exception.InsufficientStockException;
import com.masprogtechs.sales.application.system.exception.UnauthorizedAccessException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    public SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO) {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }

        User registeredBy = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (registeredBy == null) {
            throw new AuthenticationCredentialsNotFoundException("User not found.");
        }

        if (!Arrays.asList("ADMIN", "MANAGER", "OPERATOR").contains(registeredBy.getRole().name())) {
            throw new AuthorizationException("User is not authorized to create a sale.");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleItem> saleItems = new ArrayList<>();
        Sale sale = new Sale();

        for (SaleItemRequestDTO itemDTO : saleRequestDTO.getItems()) {
            Stock stock = stockRepository.findById(itemDTO.getStockId())
                    .orElseThrow(() -> new IllegalArgumentException("Stock not found for stock ID: " + itemDTO.getStockId()));

            int requestedQuantity = itemDTO.getQuantity();
            int availableQuantity = stock.getQuantity();

            System.out.println("Requested Quantity: " + requestedQuantity);
            System.out.println("Available Quantity: " + availableQuantity);

            if (availableQuantity < requestedQuantity) {
                throw new InsufficientStockException("Estoque insuficiente para efectuar a venda: " + itemDTO.getStockId());
            }

            BigDecimal subtotal = stock.getSalePrice().multiply(BigDecimal.valueOf(requestedQuantity));
            totalAmount = totalAmount.add(subtotal);

            SaleItem saleItem = new SaleItem();
            saleItem.setStock(stock);
            saleItem.setQuantity(requestedQuantity);
            saleItem.setSubtotal(subtotal);
            saleItem.setSale(sale);
            saleItems.add(saleItem);

            stock.setQuantity(availableQuantity - requestedQuantity);
            stockRepository.save(stock);
        }

        BigDecimal difference = saleRequestDTO.getAmountPaid().subtract(totalAmount);


        sale.setItems(saleItems);
        sale.setCash(saleRequestDTO.getCash());
        sale.setTotalAmount(totalAmount);
        sale.setDifference(difference);
        sale.setPayment(saleRequestDTO.getPayment());
        sale.setRegisteredBy(registeredBy);
        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setAmountPaid(saleRequestDTO.getAmountPaid());

        Sale savedSale = saleRepository.save(sale);
        return modelMapper.map(savedSale, SaleResponseDTO.class);
    }

    public List<SaleResponseDTO> getAllSales() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "MANAGER");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {

                List<Sale> sales = saleRepository.findAll();
                return sales.stream()
                        .map(sale -> modelMapper.map(sale, SaleResponseDTO.class))
                        .collect(Collectors.toList());

            } else {
                throw new AuthorizationException("O usuário não está autorizado a listar vendas.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("Usuário não esta autenticado.");
        }
    }
}
