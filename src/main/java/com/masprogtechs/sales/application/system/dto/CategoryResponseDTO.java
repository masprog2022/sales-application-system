package com.masprogtechs.sales.application.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CategoryResponseDTO {

    private String message;
    private CategoryDTO categoryDTO;

    public CategoryResponseDTO(String message, CategoryDTO categoryDTO) {
        this.message = message;
        this.categoryDTO = categoryDTO;
    }
}
