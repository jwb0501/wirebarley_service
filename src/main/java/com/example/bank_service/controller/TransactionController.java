package com.example.bank_service.controller;

import com.example.bank_service.entity.Transaction;
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

    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactions(accountNumber));
    }
}
