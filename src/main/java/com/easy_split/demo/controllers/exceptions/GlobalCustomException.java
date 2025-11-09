package com.easy_split.demo.controllers.exceptions;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalCustomException {

    private final String message = "message";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgument (IllegalArgumentException exception) {
        Map<String, String> map = new HashMap<>();
        map.put(this.message, "Illegal Argument: " +  exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> errorDataBase (DataAccessException exception) {
        Map<String, String> db = new HashMap<>();
        db.put(this.message, "Error in database: " + exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(db);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValid (MethodArgumentNotValidException exception) {
        Map<String, String> validationError = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            validationError.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> nullPointerException (NullPointerException exception) {
        Map<String, String> validationError = new HashMap<>();
        validationError.put(this.message, "Null Pointer Exception " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> httpMessageNotReadableException (HttpMessageNotReadableException exception) {
        Map<String, String> httpMessage = new HashMap<>();
        httpMessage.put(this.message, "JSON necessário para processamento de informação");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpMessage);
    }

    @ExceptionHandler(CreateExpenseException.class)
    public ResponseEntity<Map<String, String>> createExpenseException (CreateExpenseException exception) {
        Map<String, String> httpMessage = new HashMap<>();
        httpMessage.put(this.message, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpMessage);
    }

}
