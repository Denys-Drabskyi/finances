package com.example.finances.domain.service.impl;

import com.example.finances.domain.model.Account;
import com.example.finances.domain.service.AccountService;
import com.example.finances.domain.service.UserService;
import com.example.finances.persistence.exception.UserNotFoundException;
import com.example.finances.persistence.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    public Account create(Account account) {
        if (Boolean.FALSE.equals(userService.existById(account.getUserId()))) {
            throw UserNotFoundException.byId(account.getUserId());
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
