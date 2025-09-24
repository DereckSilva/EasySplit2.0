package com.easy_split.demo.exceptions;

import org.apache.coyote.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalCustomException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgument (IllegalArgumentException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Illegal Argument: " +  exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> errorDataBase (DataAccessException exception) {
        Map<String, String> db = new HashMap<>();
        db.put("message", "Error in database: " + exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(db);
    }

}
