package com.masprogtechs.sales.application.system.domain.enums;

import lombok.Data;


public enum Role {
    ADMIN("admin"),
    OPERATOR("OPERATOR");

    Role(String role) {
        this.role = role;
    }
    private String role;

    public String getRole() {
        return role;
    }


}
