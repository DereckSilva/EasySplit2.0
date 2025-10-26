package com.easy_split.demo.controllers.error;

public class CreateExpenseException extends RuntimeException {
    public CreateExpenseException(String message) {
        super(message);
    }
}
