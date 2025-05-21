package com.example.bank_service.controller;

import com.example.bank_service.dto.AccountRequest;
import com.example.bank_service.dto.ApiResponse;
import com.example.bank_service.enums.SuccessCode;
import com.example.bank_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * 계좌 등록
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createAccount(@RequestBody AccountRequest request) {
        accountService.createAccount(request.getAccountNumber(), request.getInitialBalance());
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.ACCOUNT_CREATED, null));
    }

    /**
     * 계좌 삭제
     */
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.ACCOUNT_DELETED, null));
    }

    /**
     * 입금
     */
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<ApiResponse<Void>> deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.DEPOSIT_SUCCESS, null));
    }

    /**
     * 출금
     */
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<ApiResponse<Void>> withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.WITHDRAW_SUCCESS, null));
    }

    /**
     * 이체
     */
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<Void>> transfer(@RequestParam String fromAccount,
                                           @RequestParam String toAccount,
                                           @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.TRANSFER_SUCCESS, null));
    }
}
