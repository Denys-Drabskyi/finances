package com.example.finances.persistence.jpa.dao;

import com.example.finances.persistence.jpa.entity.JpaUserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaUserDao extends JpaRepository<JpaUserEntity, Long> {}
