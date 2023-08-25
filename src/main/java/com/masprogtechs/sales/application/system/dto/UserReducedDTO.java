package com.masprogtechs.sales.application.system.dto;

import com.masprogtechs.sales.application.system.domain.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserResponseDTO {

    private String name;
    private String username;
    private String email;
    private String phone;
    private Role role;

    public UserResponseDTO(String name, String username, String email, String phone, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}
