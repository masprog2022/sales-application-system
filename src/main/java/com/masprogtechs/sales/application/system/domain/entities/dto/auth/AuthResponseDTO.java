package com.masprogtechs.sales.application.system.domain.entities.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDTO {

    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

}
