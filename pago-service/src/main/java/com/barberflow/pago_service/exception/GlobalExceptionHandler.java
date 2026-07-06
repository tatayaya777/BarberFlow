package com.barberflow.pago_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> recursoNoEncontrado(
            ResourceNotFoundException ex){

        Map<String,Object> error = new HashMap<>();

        error.put("fecha", LocalDateTime.now());
        error.put("estado",404);
        error.put("mensaje",ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> errorGeneral(
            Exception ex){

        Map<String,Object> error = new HashMap<>();

        error.put("fecha", LocalDateTime.now());
        error.put("estado",500);
        error.put("mensaje",ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}