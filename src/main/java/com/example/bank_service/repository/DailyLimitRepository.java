package com.example.bank_service.repository;

import com.example.bank_service.enums.TransactionType;
import com.example.bank_service.entity.DailyLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyLimitRepository extends JpaRepository<DailyLimit, Long> {

    Optional<DailyLimit> findByAccountNumberAndTransactionTypeAndDate(String accountNumber, TransactionType transactionType, LocalDate now);
}
