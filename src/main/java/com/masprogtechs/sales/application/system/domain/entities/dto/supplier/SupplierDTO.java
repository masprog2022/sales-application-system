package com.masprogtechs.sales.application.system.domain.entities.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masprogtechs.sales.application.system.domain.entities.dto.stock.user.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SupplierDTO {

    private Long id;
    private String name;
    @JsonProperty("registeredBy")
    private UserDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SupplierDTO(String name, UserDTO registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
