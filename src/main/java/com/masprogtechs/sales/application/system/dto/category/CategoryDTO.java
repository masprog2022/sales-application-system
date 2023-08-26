package com.masprogtechs.sales.application.system.dto.category;

import com.masprogtechs.sales.application.system.dto.user.UserReducedDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private UserReducedDTO registeredBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryDTO(Long id, String name, String description, UserReducedDTO registeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
