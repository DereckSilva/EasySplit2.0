package com.easy_split.demo.mappers;

import com.easy_split.demo.controllers.error.UtilClassException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseMapper {

    private ResponseMapper() {
        throw new UtilClassException("Classe utilitária, não pode ser instanciada.");
    }

    public static ResponseEntity<Map<String, Object>> toResponseEntity(String message, HttpStatus status, Map<String, Object> object) {
        return ResponseEntity.status(status).body(toMap(message, object));
    }

    public static Map<String, Object> toMap(String message, Map<String, Object> object) {
        object.put("message", message);
        return object;
    }
}
