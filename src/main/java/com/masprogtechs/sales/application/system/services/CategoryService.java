package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Category;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.repositories.CategoryRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.dto.CategoryDTO;
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
public class CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public CategoryDTO registerCategory(CategoryDTO categoryDTO) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "OPERATOR");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
                Category category = modelMapper.map(categoryDTO, Category.class);
                category.setRegisteredBy(registeredBy);
                category.setCreatedAt(LocalDateTime.now());
                category.setUpdatedAt(LocalDateTime.now());

                Category savedCategory = categoryRepository.save(category);

                return modelMapper.map(savedCategory, CategoryDTO.class);
            } else {
                throw new UnauthorizedAccessException("User is not authorized to register a product.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }
    }

}
