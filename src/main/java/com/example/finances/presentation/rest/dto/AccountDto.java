package com.example.finances.presentation.rest.dto;

import com.example.finances.persistence.enums.Currency;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String accountName;
    private BigDecimal balance;
    private Currency currency;
}
