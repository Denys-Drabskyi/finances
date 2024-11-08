package com.example.finances.persistence.repository;

public interface SimpleRepository<M, I> {
    M findById(I id);
    M save(M model);
    void deleteById(I id);
}
