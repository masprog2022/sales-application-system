package com.masprogtechs.sales.application.system.domain.entities.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SupplierResponseDTO {

    private Long id;
    private String name;

    public SupplierResponseDTO(String name) {

        this.name = name;
    }
}
