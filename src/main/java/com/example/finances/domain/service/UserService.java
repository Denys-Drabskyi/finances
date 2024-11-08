package com.example.finances.domain.service;

import com.example.finances.domain.model.User;

public interface UserService extends CrudService<User> {
    boolean existById(Long id);
}
