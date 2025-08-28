package com.example.auth_service.exception;

public class UnauthenticatedAccessException extends RuntimeException{
	public UnauthenticatedAccessException(String message) {
        super(message);
    }

    public UnauthenticatedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
