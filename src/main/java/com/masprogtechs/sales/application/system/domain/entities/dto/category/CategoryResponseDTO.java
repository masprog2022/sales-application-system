package com.masprogtechs.sales.application.system.domain.entities.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    private String name;

    public CategoryResponseDTO(String name) {

        this.name = name;
    }
}
