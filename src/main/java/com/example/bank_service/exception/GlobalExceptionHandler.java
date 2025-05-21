package com.example.bank_service.exception;

import com.example.bank_service.dto.ApiResponse;
import com.example.bank_service.enums.ErrorCode;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ApiResponse response = ApiResponse.error(errorCode);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 필요 시 errorCode마다 status 조절 가능
                .body(response);
    }

    // 예시: 잘못된 입력 등 다른 예외 핸들링도 추가 가능
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ApiResponse response = ApiResponse.error(ErrorCode.INVALID_INPUT);
        return ResponseEntity.badRequest().body(response);
    }

    // fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
        ApiResponse response = ApiResponse.error(ErrorCode.INVALID_INPUT); // 예외 로깅 후 적절한 코드 매핑
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
