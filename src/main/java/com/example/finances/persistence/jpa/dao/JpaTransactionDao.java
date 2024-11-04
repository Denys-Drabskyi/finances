package com.example.finances.persistence.jpa.dao;

import com.example.finances.persistence.jpa.entity.JpaTransactionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaTransactionDao extends JpaRepository<JpaTransactionEntity, Long> {}
