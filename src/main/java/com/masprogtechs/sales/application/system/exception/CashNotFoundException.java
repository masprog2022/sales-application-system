package com.masprogtechs.sales.application.system.exception;

public class CashNotFoundException extends RuntimeException {
    public CashNotFoundException() {
        super("Cash not found.");
    }

    public CashNotFoundException(String message) {
        super(message);
    }
}
