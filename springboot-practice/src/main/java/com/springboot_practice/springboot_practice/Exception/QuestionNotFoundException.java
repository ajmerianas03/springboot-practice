package com.springboot_practice.springboot_practice.Exception;


public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
