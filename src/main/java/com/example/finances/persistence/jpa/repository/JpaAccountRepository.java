package com.example.finances.persistence.jpa.repository;

import com.example.finances.domain.model.Account;
import com.example.finances.persistence.exception.AccountNotFoundException;
import com.example.finances.persistence.jpa.dao.JpaAccountDao;
import com.example.finances.persistence.jpa.entity.JpaAccountEntity;
import com.example.finances.persistence.jpa.mapper.JpaAccountEntityMapper;
import com.example.finances.persistence.repository.AccountRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {
    private final JpaAccountDao accountDao;
    private final JpaAccountEntityMapper accountMapper;

    @Override
    public Account findById(Long id) {
        var entity = accountDao.findById(id)
            .orElseThrow(() -> AccountNotFoundException.byId(id));
        return accountMapper.toModel(entity);
    }

    @Override
    public List<Account> findByUserId(Long userId) {
       return accountMapper.toModelList(accountDao.findByUserId(userId));
    }

    @Override
    public Account save(Account model) {
        var entity = model.getId() == null
            ? new JpaAccountEntity()
            : accountDao.findById(model.getId()).orElseThrow(() -> AccountNotFoundException.byId(model.getId()));
        accountMapper.updateEntityFromModel(entity, model);
        entity = accountDao.save(entity);
        return accountMapper.toModel(entity);
    }

    @Override
    public void deleteById(Long id) {
        var entity = accountDao.findById(id)
            .orElseThrow(() -> AccountNotFoundException.byId(id));
        accountDao.delete(entity);
    }
}
