package com.example.finances.persistence.jpa.repository;

import com.example.finances.persistence.repository.UserRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {}
