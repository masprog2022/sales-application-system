package com.masprogtechs.sales.application.system.domain.entities.dto.stock.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.masprogtechs.sales.application.system.domain.enums.Role;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserDTO(String name, String username, String email,
                   String phone, String password, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

}
