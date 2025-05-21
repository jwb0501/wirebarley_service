package com.example.bank_service.enums;

public enum SuccessCode {
    ACCOUNT_CREATED("ACCOUNT_CREATED", "계좌가 생성되었습니다."),
    ACCOUNT_DELETED("ACCOUNT_DELETED", "계좌가 삭제되었습니다."),
    DEPOSIT_SUCCESS("DEPOSIT_SUCCESS", "입금이 완료되었습니다."),
    WITHDRAW_SUCCESS("WITHDRAW_SUCCESS", "출금이 완료되었습니다."),
    TRANSFER_SUCCESS("TRANSFER_SUCCESS", "이체가 완료되었습니다."),
    FETCH_SUCCESS("FETCH_SUCCESS", "조회가 완료되었습니다.");

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
