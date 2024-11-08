package com.example.finances.persistence.repository;

import com.example.finances.domain.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SimpleRepository<User, Long> {
    boolean existById(Long id);
}
