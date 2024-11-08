package com.example.finances.domain.service;

public interface CrudService<E> {
    E findById(Long id);

    E create(E entity);

    E update(E entity);

    void deleteById(Long id);
}
