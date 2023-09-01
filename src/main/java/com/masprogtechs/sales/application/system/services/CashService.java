package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Cash;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashResponseDTO;
import com.masprogtechs.sales.application.system.domain.repositories.CashRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.exception.IllegalStateErrorException;
import com.masprogtechs.sales.application.system.exception.UnauthorizedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public boolean isCashOpenForUser(User user){
        return cashRepository.existsByRegisteredByAndClosingDateIsNull(user);
    }
}
