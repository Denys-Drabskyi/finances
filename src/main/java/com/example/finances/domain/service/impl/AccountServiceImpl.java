package com.example.finances.domain.service.impl;

import com.example.finances.domain.model.Account;
import com.example.finances.domain.service.AccountService;
import com.example.finances.persistence.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<Account> findByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }
}
