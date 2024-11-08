package com.example.finances.persistence.jpa.repository;

import com.example.finances.domain.model.User;
import com.example.finances.persistence.jpa.dao.JpaUserDao;
import com.example.finances.persistence.jpa.mapper.JpaUserEntityMapper;
import com.example.finances.persistence.repository.UserRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {
    private final JpaUserDao userDao;
    private final JpaUserEntityMapper userMapper;

    @Override
    public boolean existById(Long id) {
        return userDao.existsById(id);
    }

    @Override
    public User findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User save(User model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }
}
