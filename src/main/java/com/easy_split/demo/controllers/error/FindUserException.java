package com.easy_split.demo.controllers.error;

public class FindUserException extends RuntimeException{
    public FindUserException(String message) {
        super(message);
    }
}
