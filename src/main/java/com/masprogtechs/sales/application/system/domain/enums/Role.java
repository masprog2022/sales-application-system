package com.masprogtechs.sales.application.system.domain.enums;

import lombok.Data;


public enum Role {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    OPERATOR("OPERATOR");

    Role(String role) {
        this.role = role;
    }
    private String role;

    public String getRole() {
        return role;
    }


}
