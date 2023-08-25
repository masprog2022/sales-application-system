package com.masprogtechs.sales.application.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masprogtechs.sales.application.system.domain.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    @JsonProperty("registeredBy")
    private UserDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryDTO(Long id, String name, String description, UserDTO registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
