package com.example.finances.domain.model;

import com.example.finances.persistence.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private User user;
    private String accountName;
    private BigDecimal balance;
    private Currency currency;
    private LocalDateTime createdAt;
    private List<Transaction> transactions;
}
