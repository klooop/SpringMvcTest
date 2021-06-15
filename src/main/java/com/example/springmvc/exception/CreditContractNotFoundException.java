package com.example.springmvc.exception;

public class CreditContractNotFoundException extends RuntimeException{
    public CreditContractNotFoundException(String message) {
        super(message);
    }
}
