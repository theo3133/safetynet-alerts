package com.safetynet.alerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException exception) {
        logger.warn("Réponse sortante : 400 Bad Request - {}", exception.getMessage()
        );

        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}