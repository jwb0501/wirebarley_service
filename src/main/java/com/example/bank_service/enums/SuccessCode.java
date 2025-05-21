package com.example.bank_service.enums;

public enum SuccessCode {
    ACCOUNT_CREATED("S001", "계좌가 생성되었습니다."),
    ACCOUNT_DELETED("S002", "계좌가 삭제되었습니다."),
    DEPOSIT_SUCCESS("S003", "입금이 완료되었습니다."),
    WITHDRAW_SUCCESS("S004", "출금이 완료되었습니다."),
    TRANSFER_SUCCESS("S005", "이체가 완료되었습니다."),
    FETCH_SUCCESS("S006", "조회가 완료되었습니다.");

    private final String code;
    private final String message;

    SuccessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
