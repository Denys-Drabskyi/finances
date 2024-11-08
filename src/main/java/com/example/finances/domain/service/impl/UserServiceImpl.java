package com.example.finances.domain.service.impl;

import com.example.finances.domain.model.User;
import com.example.finances.domain.service.UserService;
import com.example.finances.persistence.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existById(Long id) {
        return userRepository.existById(id);
    }

    @Override
    public User findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User create(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }
}
