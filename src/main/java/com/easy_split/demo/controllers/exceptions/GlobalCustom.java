package com.easy_split.demo.controllers.exceptions;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import com.easy_split.demo.controllers.error.FindUserException;
import com.easy_split.demo.controllers.error.UtilClassException;
import com.easy_split.demo.mappers.ResponseMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalCustom {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgument (IllegalArgumentException exception) {
        return ResponseMapper.toResponseEntity("Illegal Argument: " +  exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> errorDataBase (DataAccessException exception) {
        return ResponseMapper.toResponseEntity("Error in database: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValid (MethodArgumentNotValidException exception) {
        Map<String, String> validationError = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error ->
            validationError.put(((FieldError) error).getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> nullPointerException (NullPointerException exception) {
        return ResponseMapper.toResponseEntity( "Null Pointer Exception " + exception.getMessage(), HttpStatus.BAD_REQUEST, new HashMap<>());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> httpMessageNotReadableException (HttpMessageNotReadableException exception) {
        return ResponseMapper.toResponseEntity( "JSON necessário para processamento de informação", HttpStatus.BAD_REQUEST, new HashMap<>());
    }

    @ExceptionHandler(CreateExpenseException.class)
    public ResponseEntity<Map<String, Object>> createExpenseException (CreateExpenseException exception) {
        return ResponseMapper.toResponseEntity( exception.getMessage(), HttpStatus.BAD_REQUEST, new HashMap<>());
    }

    @ExceptionHandler(FindUserException.class)
    public ResponseEntity<Map<String, Object>> findUserException (FindUserException exception) {
        return ResponseMapper.toResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST, new HashMap<>());
    }

    @ExceptionHandler(UtilClassException.class)
    public ResponseEntity<Map<String, Object>> utilClassException (UtilClassException exception) {
        return ResponseMapper.toResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST, new HashMap<>());
    }

    @ExceptionHandler(InterruptedIOException.class)
    public ResponseEntity<Map<String, Object>> interruptedIOException (InterruptedIOException exception) {
        return ResponseMapper.toResponseEntity("Tempo de execução excedido", HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>());
    }

}
