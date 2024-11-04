package com.example.finances.persistence.repository;

import com.example.finances.domain.model.Account;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {
    List<Account> findByUserId(Long userId);
}
