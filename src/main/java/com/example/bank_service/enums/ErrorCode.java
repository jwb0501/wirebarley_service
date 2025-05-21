package com.example.bank_service.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND", "계좌를 찾을 수 없습니다."),
    DUPLICATE_ACCOUNT("DUPLICATE_ACCOUNT", "이미 존재하는 계좌번호입니다."),
    INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS", "잔액이 부족합니다."),
    EXCEEDS_WITHDRAW_LIMIT("EXCEEDS_WITHDRAW_LIMIT", "일 출금 한도를 초과했습니다."),
    EXCEEDS_TRANSFER_LIMIT("EXCEEDS_TRANSFER_LIMIT", "일 이체 한도를 초과했습니다."),
    INVALID_INPUT("INVALID_INPUT", "요청 값이 올바르지 않습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public String getMessage() {
        return message;
    }
}
