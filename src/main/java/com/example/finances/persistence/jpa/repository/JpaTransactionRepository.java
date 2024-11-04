package com.example.finances.persistence.jpa.repository;

import com.example.finances.persistence.repository.TransactionRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTransactionRepository implements TransactionRepository {}
