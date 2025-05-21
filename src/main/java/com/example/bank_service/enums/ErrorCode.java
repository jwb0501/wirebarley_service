package com.example.bank_service.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ACCOUNT_NOT_FOUND("E001", "계좌를 찾을 수 없습니다."),
    DUPLICATE_ACCOUNT("E002", "이미 존재하는 계좌번호입니다."),
    INSUFFICIENT_FUNDS("E003", "잔액이 부족합니다."),
    EXCEEDS_WITHDRAW_LIMIT("E004", "일 출금 한도를 초과했습니다."),
    EXCEEDS_TRANSFER_LIMIT("E005", "일 이체 한도를 초과했습니다."),
    INVALID_INPUT("E006", "요청 값이 올바르지 않습니다."),
    DUPLICATE_UPDATE("E007", "동시에 수정된 요청이 존재합니다. 다시 시도해주세요.");

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
