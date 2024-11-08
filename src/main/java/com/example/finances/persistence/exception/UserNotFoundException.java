package com.example.finances.persistence.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserNotFoundException extends EntityNotFoundException {
    public static UserNotFoundException byId(Long id) {
        var message = String.format("User with id: %s not found", id);
        return new UserNotFoundException(message);
    }
}
