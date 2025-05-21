package com.example.bank_service.controller;

import com.example.bank_service.dto.ApiResponse;
import com.example.bank_service.entity.Transaction;
import com.example.bank_service.enums.SuccessCode;
import com.example.bank_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * 계좌 조회
     * 내역 최신순
     * @param accountNumber
     * @return
     */
    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(ApiResponse.ok(SuccessCode.FETCH_SUCCESS,transactionService.getTransactions(accountNumber)));
    }
}
