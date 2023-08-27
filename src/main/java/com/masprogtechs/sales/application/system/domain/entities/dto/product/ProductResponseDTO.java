package com.masprogtechs.sales.application.system.domain.entities.dto.product;


import com.masprogtechs.sales.application.system.domain.entities.dto.category.CategoryResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.supplier.SupplierResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;

    public ProductResponseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
