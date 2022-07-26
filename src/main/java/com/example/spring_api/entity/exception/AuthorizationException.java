package com.example.spring_api.entity.exception;

public class AuthorizationException extends Exception{
    public AuthorizationException(String message) {
        super(message);
    }
}
