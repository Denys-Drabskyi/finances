package com.example.finances.persistence.jpa.dao;

import com.example.finances.persistence.jpa.entity.JpaAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JpaAccountDao extends JpaRepository<JpaAccountEntity, Long> {
    List<JpaAccountEntity> findByUserId(Long userId);
}
