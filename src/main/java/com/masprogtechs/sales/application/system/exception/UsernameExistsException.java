package com.masprogtechs.sales.application.system.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String message) {
        super(message);
    }
}
