package com.example.finances.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.finances.domain.model.Account;
import com.example.finances.domain.service.impl.AccountServiceImpl;
import com.example.finances.persistence.repository.AccountRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
  @Mock
  private AccountRepository repository;

  @InjectMocks
  private AccountServiceImpl service;

  private static final Account ACCOUNT_1 = Account.builder().build();
  private static final Account ACCOUNT_2 = Account.builder().build();

  @Test
  @DisplayName("Get entity test")
  void testCase01() {
    when(repository.findByUserId(anyLong())).thenReturn(List.of(ACCOUNT_1, ACCOUNT_2));

    var rez = service.findByUserId(1L);

    verify(repository).findByUserId(anyLong());
    assertEquals(List.of(ACCOUNT_1, ACCOUNT_2), rez);
  }
}