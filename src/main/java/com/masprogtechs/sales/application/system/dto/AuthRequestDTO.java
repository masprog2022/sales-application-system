package com.masprogtechs.sales.application.system.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String username;
    private String password;

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
