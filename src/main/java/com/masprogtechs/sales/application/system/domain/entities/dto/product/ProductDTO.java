package com.masprogtechs.sales.application.system.domain.entities.dto.product;


import com.masprogtechs.sales.application.system.domain.entities.dto.supplier.SupplierResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.category.CategoryResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.stock.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private CategoryResponseDTO category;
    private SupplierResponseDTO supplier;
    private UserReducedDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDTO(Long id, String name, String description, CategoryResponseDTO category,
                      SupplierResponseDTO supplier, UserReducedDTO registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
