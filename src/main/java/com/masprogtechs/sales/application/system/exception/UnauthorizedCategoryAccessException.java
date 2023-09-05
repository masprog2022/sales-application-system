package com.masprogtechs.sales.application.system.exception;

public class UnauthorizedCategoryAccessException extends RuntimeException {
    public UnauthorizedCategoryAccessException(String message) {
        super(message);
    }
}
