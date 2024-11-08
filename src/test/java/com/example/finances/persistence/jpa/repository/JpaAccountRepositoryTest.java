package com.example.finances.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.finances.domain.model.Account;
import com.example.finances.persistence.exception.AccountNotFoundException;
import com.example.finances.persistence.jpa.dao.JpaAccountDao;
import com.example.finances.persistence.jpa.entity.JpaAccountEntity;
import com.example.finances.persistence.jpa.mapper.JpaAccountEntityMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class JpaAccountRepositoryTest {

    @Mock
    private JpaAccountDao accountDao;

    @Mock
    private JpaAccountEntityMapper accountMapper;

    @InjectMocks
    private JpaAccountRepository repository;

    private static final Long ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Account ACCOUNT_WITH_ID = Account.builder().id(ID).build();
    private static final Account ACCOUNT_WITHOUT_ID = Account.builder().id(null).build();
    private static final JpaAccountEntity ENTITY = new JpaAccountEntity();
    private static final List<JpaAccountEntity> ENTITY_LIST = List.of(ENTITY);
    private static final List<Account> ACCOUNT_LIST = List.of(ACCOUNT_WITH_ID);

    @Test
    @DisplayName("findById throws AccountNotFoundException")
    void testCase01() {
        when(accountDao.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> repository.findById(ID));
    }

    @Test
    @DisplayName("findById success")
    void testCase02() {
        when(accountDao.findById(ID)).thenReturn(Optional.of(ENTITY));
        when(accountMapper.toModel(ENTITY)).thenReturn(ACCOUNT_WITH_ID);

        var result = repository.findById(ID);

        verify(accountDao, times(1)).findById(ID);
        verify(accountMapper, times(1)).toModel(ENTITY);
        assertEquals(ACCOUNT_WITH_ID, result);
    }

    @Test
    @DisplayName("findByUserId success")
    void testCase03() {
        when(accountDao.findByUserId(USER_ID)).thenReturn(ENTITY_LIST);
        when(accountMapper.toModelList(ENTITY_LIST)).thenReturn(ACCOUNT_LIST);

        var result = repository.findByUserId(USER_ID);

        verify(accountDao, times(1)).findByUserId(USER_ID);
        verify(accountMapper, times(1)).toModelList(ENTITY_LIST);
        assertEquals(ACCOUNT_LIST, result);
    }

    @Test
    @DisplayName("save with existing ID - success")
    void testCase04() {
        when(accountDao.findById(ACCOUNT_WITH_ID.getId())).thenReturn(Optional.of(ENTITY));
        doNothing().when(accountMapper).updateEntityFromModel(ENTITY, ACCOUNT_WITH_ID);
        when(accountDao.save(ENTITY)).thenReturn(ENTITY);
        when(accountMapper.toModel(ENTITY)).thenReturn(ACCOUNT_WITH_ID);

        var result = repository.save(ACCOUNT_WITH_ID);

        verify(accountDao, times(1)).findById(ACCOUNT_WITH_ID.getId());
        verify(accountMapper, times(1)).updateEntityFromModel(ENTITY, ACCOUNT_WITH_ID);
        verify(accountDao, times(1)).save(ENTITY);
        verify(accountMapper, times(1)).toModel(ENTITY);
        assertEquals(ACCOUNT_WITH_ID, result);
    }

    @Test
    @DisplayName("save with null ID - success")
    void testCase05() {
        doNothing().when(accountMapper).updateEntityFromModel(any(JpaAccountEntity.class), eq(ACCOUNT_WITHOUT_ID));
        when(accountDao.save(any(JpaAccountEntity.class))).thenReturn(new JpaAccountEntity());
        when(accountMapper.toModel(any(JpaAccountEntity.class))).thenReturn(ACCOUNT_WITHOUT_ID);

        var result = repository.save(ACCOUNT_WITHOUT_ID);

        verify(accountDao, times(0)).findById(anyLong());
        verify(accountMapper, times(1)).updateEntityFromModel(any(JpaAccountEntity.class), eq(ACCOUNT_WITHOUT_ID));
        verify(accountDao, times(1)).save(any(JpaAccountEntity.class));
        verify(accountMapper, times(1)).toModel(any(JpaAccountEntity.class));
        assertEquals(ACCOUNT_WITHOUT_ID, result);
    }

    @Test
    @DisplayName("deleteById throws AccountNotFoundException")
    void testCase06() {
        when(accountDao.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> repository.deleteById(ID));
    }

    @Test
    @DisplayName("deleteById success")
    void testCase07() {
        when(accountDao.findById(ID)).thenReturn(Optional.of(ENTITY));
        doNothing().when(accountDao).delete(ENTITY);

        repository.deleteById(ID);

        verify(accountDao, times(1)).findById(ID);
        verify(accountDao, times(1)).delete(ENTITY);
    }

    @Test
    @DisplayName("save with non-existing ID - throws AccountNotFoundException")
    void testCase08() {
        when(accountDao.findById(ID)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> repository.save(ACCOUNT_WITH_ID));

        verify(accountDao, times(1)).findById(ID);
        verify(accountMapper, times(0)).updateEntityFromModel(any(JpaAccountEntity.class), any(Account.class));
        verify(accountDao, times(0)).save(any(JpaAccountEntity.class));
    }

}
