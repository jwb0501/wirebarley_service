package com.example.bank_service.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    ACCOUNT_NOT_FOUND("A001", "계좌를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ACCOUNT_ALREADY_EXISTS("A002", "이미 존재하는 계좌번호입니다.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE("A003", "잔액이 부족합니다.", HttpStatus.BAD_REQUEST),
    EXCEED_DAILY_LIMIT("A004", "일 한도를 초과했습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("S001", "서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public HttpStatus getStatus() { return status; }
}
