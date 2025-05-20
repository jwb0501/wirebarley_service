package com.example.bank_service.service;

import com.example.bank_service.enums.TransactionType;
import com.example.bank_service.entity.DailyLimit;
import com.example.bank_service.repository.DailyLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyLimitService {

    private final DailyLimitRepository dailyLimitRepo;

    public BigDecimal getTodayTotal(String accountNumber, TransactionType transactionType) {
        return dailyLimitRepo.findByAccountNumberAndTransactionTypeAndDate(
                        accountNumber,
                        transactionType,
                        LocalDate.now()
                ).map(DailyLimit::getTotalAmount)
                .orElse(BigDecimal.ZERO);
    }

    @Transactional
    public void addAmount(String accountNumber, TransactionType transactionType, BigDecimal amount) {
        DailyLimit dailyLimit = dailyLimitRepo.findByAccountNumberAndTransactionTypeAndDate(
                accountNumber,
                transactionType,
                LocalDate.now()
        ).orElse(
                DailyLimit.builder()
                        .accountNumber(accountNumber)
                        .transactionType(transactionType)
                        .date(LocalDate.now())
                        .totalAmount(BigDecimal.ZERO)
                        .build()
        );

        dailyLimit.setTotalAmount(dailyLimit.getTotalAmount().add(amount));
        dailyLimitRepo.save(dailyLimit);
    }
}
