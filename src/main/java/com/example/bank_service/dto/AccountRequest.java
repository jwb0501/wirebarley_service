package com.example.bank_service.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private String accountNumber;
    private BigDecimal initialBalance;
}
