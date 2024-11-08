package com.example.finances.persistence.exception;

import lombok.experimental.StandardException;

@StandardException
public class AccountNotFoundException extends EntityNotFoundException {
    public static AccountNotFoundException byId(Long id) {
        var message = String.format("Account with id: %s not found", id);
        return new AccountNotFoundException(message);
    }
}
