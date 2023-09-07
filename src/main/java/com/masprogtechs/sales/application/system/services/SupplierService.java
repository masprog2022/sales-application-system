package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Supplier;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.repositories.SupplierRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.domain.entities.dto.supplier.SupplierDTO;
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
public class SupplierService {

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private SupplierRepository supplierRepository;

        @Autowired
        private UserRepository userRepository;


        public SupplierDTO registerSupplier(SupplierDTO supplierDTO) {
            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

                User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

                List<String> allowedRoles = Arrays.asList("ADMIN", "MANAGER");

                if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
                    Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
                    supplier.setRegisteredBy(registeredBy);
                    supplier.setCreatedAt(LocalDateTime.now());
                    supplier.setUpdatedAt(LocalDateTime.now());

                    Supplier savedSupplier = supplierRepository.save(supplier);

                    return modelMapper.map(savedSupplier, SupplierDTO.class);
                } else {
                    throw new AuthorizationException("O usuário não está autorizado para registar fornecedor.");
                }
            } else {
                throw new AuthenticationCredentialsNotFoundException("Usuário não esta autenticado.");
            }
        }
}
