package com.example.demo.Exception1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InsufficientFundsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public InsufficientFundsException(String message) {
        super(message);
    }
}
