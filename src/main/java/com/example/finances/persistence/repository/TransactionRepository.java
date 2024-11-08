package com.example.finances.persistence.repository;

import com.example.finances.domain.model.Account;

import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends SimpleRepository<Account, Long> {}
