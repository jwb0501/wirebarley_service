package com.example.bank_service.service;

import com.example.bank_service.enums.TransactionType;
import com.example.bank_service.entity.Transaction;
import com.example.bank_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepo;

    public void saveLog(String accountNumber,
                        TransactionType type,
                        BigDecimal amount,
                        BigDecimal balanceAfter,
                        String counterpartyAccount){
        transactionRepo.save(
                Transaction.builder()
                        .accountNumber(accountNumber)
                        .type(type)
                        .amount(amount)
                        .balanceAfter(balanceAfter)
                        .transactionAt(LocalDateTime.now())
                        .counterpartyAccount(counterpartyAccount)
                        .build()
        );
    }

    public List<Transaction> getTransactions(String accountNumber){
        return transactionRepo.findByAccountNumberOrderByTransactionAtDesc(accountNumber);
    }
}
