package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.Category;
import com.masprogtechs.sales.application.system.domain.entities.Product;
import com.masprogtechs.sales.application.system.domain.entities.Supplier;
import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.repositories.CategoryRepository;
import com.masprogtechs.sales.application.system.domain.repositories.ProductRepository;
import com.masprogtechs.sales.application.system.domain.repositories.SupplierRepository;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.domain.entities.dto.product.ProductDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
   @Autowired
    private CategoryRepository categoryRepository;

   @Autowired
    private SupplierRepository supplierRepository;
   @Autowired
    private UserRepository userRepository;

   @Autowired
    private UserService userService;

   @Autowired
    private ModelMapper modelMapper;

    public ProductDTO registerProduct(ProductDTO productDTO) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            User registeredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            List<String> allowedRoles = Arrays.asList("ADMIN", "OPERATOR");

            if (registeredBy != null && allowedRoles.contains(registeredBy.getRole().name())) {
                Product product = modelMapper.map(productDTO, Product.class);
                product.setRegisteredBy(registeredBy);
                product.setCreatedAt(LocalDateTime.now());
                product.setUpdatedAt(LocalDateTime.now());

                if (productDTO.getCategory() != null) {
                    Category category = categoryRepository.findById(productDTO.getCategory().getId()).orElse(null);
                    product.setCategory(category);
                }

                if (productDTO.getSupplier() != null) {
                    Supplier supplier = supplierRepository.findById(productDTO.getSupplier().getId()).orElse(null);
                    product.setSupplier(supplier);
                }

                Product savedProduct = productRepository.save(product);

                UserReducedDTO registeredByReducedDTO = userService.mapToReducedDTO(registeredBy);
                productDTO.setRegisteredBy(registeredByReducedDTO);

                return modelMapper.map(savedProduct, ProductDTO.class);
            } else {
                throw new UnauthorizedAccessException("User is not authorized to register a product.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }
    }
}
