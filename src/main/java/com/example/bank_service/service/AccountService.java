package com.example.bank_service.service;

import com.example.bank_service.dto.ApiResponse;
import com.example.bank_service.enums.ErrorCode;
import com.example.bank_service.enums.TransactionType;
import com.example.bank_service.entity.Account;
import com.example.bank_service.exception.ApiException;
import com.example.bank_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;

    private final DailyLimitService dailyLimitService;

    private final TransactionService transactionService;

    /**
     * 계좌 생성
     * @param accountNumber
     * @param balance
     * @return
     */
    @Transactional
    public Account createAccount(String accountNumber, BigDecimal balance){
        if(accountRepo.existsByAccountNumber(accountNumber)){
            throw new ApiException(ErrorCode.DUPLICATE_ACCOUNT);
        }

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .isDeleted(false)
                .build();

        return accountRepo.save(account);
    }

    /**
     * 계좌 삭제
     * @param accountNumber
     */
    @Transactional
    public void deleteAccount(String accountNumber){
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOT_FOUND));

        account.setDeleted(true);
        account.setDeletedAt(LocalDateTime.now());

        accountRepo.save(account);
    }

    /**
     * 입금
     * @param accountNumber
     * @param amount
     */
    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOT_FOUND));

        account.setBalance(account.getBalance().add(amount));

        accountRepo.save(account);

        // 거래내역 저장
        transactionService.saveLog(accountNumber, TransactionType.DEPOSIT, amount, account.getBalance(), null);
    }

    /**
     * 출금
     * 1일 최대 1,000,000원
     * @param accountNumber
     * @param amount
     */
    @Transactional
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (amount.compareTo(new BigDecimal("1000000")) > 0) {
            throw new ApiException(ErrorCode.EXCEEDS_WITHDRAW_LIMIT);
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new ApiException(ErrorCode.INSUFFICIENT_FUNDS);
        }

        account.setBalance(account.getBalance().subtract(amount));

        accountRepo.save(account);

        // 거래내역 저장
        transactionService.saveLog(accountNumber, TransactionType.WITHDRAW, amount, account.getBalance(), null);
    }

    /**
     * 이체
     * 1일 최대 3,000,000원
     * 수수료 1% 부과
     * @param fromAccountNumber
     * @param toAccountNumber
     * @param amount
     */
    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        BigDecimal fee = amount.multiply(new BigDecimal("0.01")).setScale(0, RoundingMode.DOWN);
        BigDecimal totalAmount = amount.add(fee);
        BigDecimal todayTotal = dailyLimitService.getTodayTotal(fromAccountNumber, TransactionType.TRANSFER);
        BigDecimal limit = new BigDecimal("3000000");

        if (todayTotal.add(totalAmount).compareTo(limit) > 0) {
            throw new ApiException(ErrorCode.EXCEEDS_TRANSFER_LIMIT);
        }

        Account from = accountRepo.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOT_FOUND));
        Account to = accountRepo.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (from.getBalance().compareTo(totalAmount) < 0) {
            throw new ApiException(ErrorCode.INSUFFICIENT_FUNDS);
        }

        from.setBalance(from.getBalance().subtract(totalAmount));
        to.setBalance(to.getBalance().add(amount));

        accountRepo.save(from);
        accountRepo.save(to);

        dailyLimitService.addAmount(fromAccountNumber, TransactionType.TRANSFER, totalAmount);

        // 거래내역 저장
        transactionService.saveLog(from.getAccountNumber(), TransactionType.TRANSFER, amount, from.getBalance(), to.getAccountNumber());
        transactionService.saveLog(to.getAccountNumber(), TransactionType.DEPOSIT, amount, to.getBalance(), from.getAccountNumber());
    }


}
