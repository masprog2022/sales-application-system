package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Category;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.repositories.CategoryRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.dto.CategoryDTO;
import com.masprogtechs.sales.application.system.exception.UnauthorizedAccessException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public CategoryDTO registerProduct(CategoryDTO categoryDTO, String loggedInUsername) {
        User registeredBy = userRepository.findByUsername(loggedInUsername);

        List<String> allowedRoles = Arrays.asList("ADMIN", "OPERATOR");

        if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
            Category category = modelMapper.map(categoryDTO, Category.class);
            category.setRegisteredBy(registeredBy);

            Category savedProduct = productRepository.save(category);

            return modelMapper.map(savedProduct, CategoryDTO.class);
        } else {
            throw new UnauthorizedAccessException("User is not authorized to register a product.");
        }
    }


}
