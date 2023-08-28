package com.masprogtechs.sales.application.system.domain.entities.dto.stock.user;

import com.masprogtechs.sales.application.system.domain.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserReducedDTO {

    private String name;
    private String username;
    private String email;
    private String phone;

    public UserReducedDTO(String name, String username, String email, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
