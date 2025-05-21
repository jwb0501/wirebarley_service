package com.example.bank_service.dto;

import com.example.bank_service.enums.ErrorCode;
import com.example.bank_service.enums.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(SuccessCode code, T data) {
        return new ApiResponse<>(true, code.code(), code.message(), data);
    }

    public static <T> ApiResponse<T> error(ErrorCode code) {
        return new ApiResponse<>(false, code.code(), code.message(), null);
    }
}
