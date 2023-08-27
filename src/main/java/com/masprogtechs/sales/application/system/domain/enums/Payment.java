package com.masprogtechs.sales.application.system.domain.enums;

public enum Payment {

    DINHEIRO("DINHEIRO"),
    CARTAO("CARTÃO");

    private final String name;

    public String getName(){
        return name;
    }

    private Payment(String name){
        this.name = name;
    }
}
