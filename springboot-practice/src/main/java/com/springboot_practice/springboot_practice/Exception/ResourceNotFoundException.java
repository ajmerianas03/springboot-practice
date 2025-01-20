package com.springboot_practice.springboot_practice.Exception;


import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    private final HttpStatus status;


    public ResourceNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' not found", resourceName, fieldName, fieldValue));
        this.status = HttpStatus.NOT_FOUND; // HTTP 404 Not Found
    }

    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }
}
