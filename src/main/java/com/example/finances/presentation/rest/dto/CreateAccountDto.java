package com.example.finances.presentation.rest.dto;

import com.example.finances.persistence.enums.Currency;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    @NotNull(message = "accountName must not be null")
    private String accountName;
    @NotNull(message = "userId must not be null")
    private Long userId;
    @NotNull(message = "currency must not be null")
    private Currency currency;
}
