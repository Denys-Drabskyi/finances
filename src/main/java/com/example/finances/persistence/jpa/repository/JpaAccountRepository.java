package com.example.finances.persistence.jpa.repository;

import com.example.finances.domain.model.Account;
import com.example.finances.persistence.jpa.dao.JpaAccountDao;
import com.example.finances.persistence.jpa.mapper.JpaAccountEntityMapper;
import com.example.finances.persistence.repository.AccountRepository;
import com.example.finances.persistence.mapper.CycleAvoidingMappingContext;

import org.springframework.stereotype.Repository;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {
    private final JpaAccountDao accountDao;
    private final JpaAccountEntityMapper accountMapper;

    @Override
    public List<Account> findByUserId(Long userId) {
       return accountMapper.toModelList(accountDao.findByUserId(userId), new CycleAvoidingMappingContext());
    }
}
