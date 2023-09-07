package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.*;
import com.masprogtechs.sales.application.system.domain.repositories.ProductRepository;
import com.masprogtechs.sales.application.system.domain.repositories.StockRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.domain.entities.dto.stock.StockDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import com.masprogtechs.sales.application.system.exception.AuthorizationException;
import com.masprogtechs.sales.application.system.exception.UnauthorizedAccessException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public StockDTO registerStock(StockDTO stockDTO) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "MANAGER");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
                Stock stock = modelMapper.map(stockDTO, Stock.class);
                stock.setRegisteredBy(registeredBy);
                stock.setCreatedAt(LocalDateTime.now());
                stock.setUpdatedAt(LocalDateTime.now());

                if (stockDTO.getProduct() != null) {
                    Product product = productRepository.findById(stockDTO.getProduct().getId()).orElse(null);
                    stock.setProduct(product);
                }

                Stock savedStock = stockRepository.save(stock);

                UserReducedDTO registeredByReducedDTO = userService.mapToReducedDTO(registeredBy);
                stockDTO.setRegisteredBy(registeredByReducedDTO);

                return modelMapper.map(savedStock, StockDTO.class);
            } else {
                throw new AuthorizationException("O usuário não está autorizado para registar estoque.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }
    }

}
