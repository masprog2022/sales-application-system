package com.masprogtechs.sales.application.system.domain.entities.dto.product;


import lombok.Data;
import lombok.NoArgsConstructor;

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
