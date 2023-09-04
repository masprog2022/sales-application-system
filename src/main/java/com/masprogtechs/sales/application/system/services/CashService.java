package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.Sale;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashClosedRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashResponseDTO;
import com.masprogtechs.sales.application.system.domain.repositories.CashRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.exception.CashNotFoundException;
import com.masprogtechs.sales.application.system.exception.IllegalStateErrorException;
import com.masprogtechs.sales.application.system.exception.UnauthorizedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CashService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CashRepository cashRepository;

    @Autowired
    private UserRepository userRepository;


    public CashResponseDTO openCash(CashRequestDTO cashDTO) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "OPERATOR");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
                if(isCashOpenForUser(registeredBy)){
                    throw new IllegalStateErrorException("Usuário já tem o caixa aberto.");
                }
                Cash cashOpen = modelMapper.map(cashDTO, Cash.class);
                cashOpen.setRegisteredBy(registeredBy);
                cashOpen.setOpeningDate(LocalDateTime.now());

                Cash savedOpenCash = cashRepository.save(cashOpen);

                return modelMapper.map(savedOpenCash, CashResponseDTO.class);
            } else {
                throw new UnauthorizedException("User is not authorized to open cash.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }
    }

    public CashResponseDTO closeCash(CashClosedRequestDTO closeRequestDTO){

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "OPERATOR");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {

        // Verifique se o caixa com o ID fornecido existe e está aberto
                Long cashId = closeRequestDTO.getCashId();
                Cash cashToClose = cashRepository.findByIdAndClosingDateIsNull(cashId)
                        .orElseThrow(() -> new CashNotFoundException("Caixa não encontrado: " + cashId));

                // Verifique se o closingBalance é válido (maior ou igual a zero)
                BigDecimal closingBalance = closeRequestDTO.getClosingBalance();
                if (closingBalance.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalStateErrorException("O saldo final deve ser maior ou igual a zero.");
                }

                // Verifique se o usuário autenticado é o mesmo que abriu o caixa
                User openedBy = cashToClose.getRegisteredBy();
                if(!registeredBy.equals(openedBy)){
                    throw new UnauthorizedException("Somente o usuário que abriu o caixa pode fechá-lo");
                }


             // actualizar o closingBalance e a data de fechamento do caixa
                cashToClose.setClosingBalance(closingBalance);
                cashToClose.setRegisteredBy(registeredBy);
                cashToClose.setClosingDate(LocalDateTime.now());

       // calcular o total das vendas desde abertura do caixa

        BigDecimal salesTotal = calculateSalesTotal(cashToClose);
                cashToClose.setSalesTotal(salesTotal);

        // Salve as alterações no caixa

        Cash cashClosed = cashRepository.save(cashToClose);

        return modelMapper.map(cashClosed, CashResponseDTO.class);
            } else {
                throw new UnauthorizedException("User is not authorized to open cash.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }

    }

    public boolean isCashOpenForUser(User user){
        return cashRepository.existsByRegisteredByAndClosingDateIsNull(user);
    }

    public BigDecimal calculateSalesTotal(Cash cash){

        BigDecimal total = BigDecimal.ZERO;
        for (Sale sale : cash.getSales()) {
            total = total.add(sale.getTotalAmount());
        }

        return total;
    }
}

