package com.example.bank_service.exception;

import com.example.bank_service.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        ErrorCode code = ex.getErrorCode();
        return buildResponse(code);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        return buildResponse(ErrorCode.INTERNAL_ERROR);
    }

    private ResponseEntity<Object> buildResponse(ErrorCode code) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", code.getStatus().value());
        body.put("errorCode", code.getCode());
        body.put("message", code.getMessage());

        return new ResponseEntity<>(body, code.getStatus());
    }
}
