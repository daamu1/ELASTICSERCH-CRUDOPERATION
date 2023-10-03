package com.elastic.exception;

import com.elastic.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ProductNotFoundException exception) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST)
                        .time(LocalDateTime.now())
                        .message(exception.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
