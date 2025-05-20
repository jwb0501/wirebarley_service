package com.example.bank_service;

import com.example.bank_service.dto.AccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 계좌_생성_API_테스트() throws Exception {
        AccountRequest request = new AccountRequest("12345678", new BigDecimal("50000"));

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("계좌가 생성되었습니다."));
    }

    @Test
    void 계좌_삭제_API_테스트() throws Exception {
        String accountNumber = "12345678";

        mockMvc.perform(delete("/api/accounts/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Account deleted successfully."));
    }

    @Test
    void 입금_API_테스트() throws Exception {
        mockMvc.perform(post("/api/accounts/12345678/deposit")
                        .param("amount", "100000"))
                .andExpect(status().isOk())
                .andExpect(content().string("입금이 완료되었습니다."));
    }

    @Test
    void 출금_API_테스트() throws Exception {
        mockMvc.perform(post("/api/accounts/12345678/withdraw")
                        .param("amount", "50000"))
                .andExpect(status().isOk())
                .andExpect(content().string("출금이 완료되었습니다."));
    }

    @Test
    void 이체_API_테스트() throws Exception {
        mockMvc.perform(post("/api/accounts/transfer")
                        .param("fromAccount", "12345678")
                        .param("toAccount", "87654321")
                        .param("amount", "10000"))
                .andExpect(status().isOk())
                .andExpect(content().string("이체가 완료되었습니다."));
    }

}
