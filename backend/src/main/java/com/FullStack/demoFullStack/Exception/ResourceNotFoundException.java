package com.FullStack.demoFullStack.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND) // Simple and declarative exception handling
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // for serialization

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
