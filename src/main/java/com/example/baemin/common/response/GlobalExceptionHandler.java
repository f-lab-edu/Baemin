package com.example.baemin.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
        log.error("CustomException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.valueOf(Integer.parseInt(e.getResponseCode().getCode())))
                .body(ApiResponse.error(e.getResponseCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ResponseCode.INVALID_INPUT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error(ResponseCode.SERVER_ERROR));
    }
}