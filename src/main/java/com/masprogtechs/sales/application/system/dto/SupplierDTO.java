package com.masprogtechs.sales.application.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
