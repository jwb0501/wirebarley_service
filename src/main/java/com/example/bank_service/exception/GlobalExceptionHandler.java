package com.example.bank_service.exception;

import com.example.bank_service.dto.ApiResponse;
import com.example.bank_service.enums.ErrorCode;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        // 메시지 기준으로 적절한 ErrorCode 매핑
        ErrorCode code = matchErrorCode(ex.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.error(code));
    }

    private ErrorCode matchErrorCode(String msg) {
        for (ErrorCode e : ErrorCode.values()) {
            if (e.message().equals(msg)) return e;
        }
        return ErrorCode.INVALID_INPUT;
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ApiResponse<Void>> handleOptimisticLock(OptimisticLockingFailureException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ErrorCode.DUPLICATE_UPDATE));
    }
}
