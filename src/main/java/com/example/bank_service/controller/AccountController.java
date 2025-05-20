package com.example.bank_service.controller;

import com.example.bank_service.dto.AccountRequest;
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
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest request) {
        accountService.createAccount(request.getAccountNumber(), request.getInitialBalance());
        return ResponseEntity.ok("계좌가 생성되었습니다.");
    }

    /**
     * 계좌 삭제
     */
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("계좌가 삭제되었습니다.");
    }

    /**
     * 입금
     */
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok("입금이 완료되었습니다.");
    }

    /**
     * 출금
     */
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok("출금이 완료되었습니다.");
    }

    /**
     * 이체
     */
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccount,
                                           @RequestParam String toAccount,
                                           @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok("이체가 완료되었습니다.");
    }
}
