package com.springboot_practice.springboot_practice.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();


        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage()); // Add the exception message
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT); // HTTP 409 Conflict
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        String errorMessage = "Invalid input: Expected a " + ex.getRequiredType().getSimpleName() +
                " but received '" + ex.getValue() + "'.";
        errorDetails.put("error", errorMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {


        Map<String, Object> errorDetails = new HashMap<>();


        errorDetails.put("timestamp", System.currentTimeMillis());


        errorDetails.put("status", ex.getStatus().value());


        errorDetails.put("message", ex.getMessage());


        errorDetails.put("error", "Resource Not Found");


        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", "The URL you are trying to access does not exist.");
        errorDetails.put("error", "Not Found");


        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }





    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // Handle other general exceptions
        String errorMessage = "An unexpected error occurred. Please try again later.";
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
