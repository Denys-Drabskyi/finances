package com.example.finances.domain.service;

import com.example.finances.domain.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findByUserId(Long userId);
}