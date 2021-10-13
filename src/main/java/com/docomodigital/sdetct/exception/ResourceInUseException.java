package com.docomodigital.sdetct.exception;

public class ResourceInUseException extends RuntimeException {
    public ResourceInUseException() {
    }

    public ResourceInUseException(String message) {
        super(message);
    }

    public ResourceInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
