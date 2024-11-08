package com.example.finances.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.finances.domain.model.Account;
import com.example.finances.domain.service.UserService;
import com.example.finances.domain.service.impl.AccountServiceImpl;
import com.example.finances.persistence.exception.UserNotFoundException;
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
  private AccountRepository accountRepository;

  @Mock
  private UserService userService;

  @InjectMocks
  private AccountServiceImpl service;

  private static final Long ID = 1L;
  private static final Long USER_ID = 2L;
  private static final Account ACCOUNT = Account.builder().build();
  private static final List<Account> ACCOUNT_LIST = List.of(ACCOUNT);

  @Test
  @DisplayName("findById throws exception")
  void testCase01() {
    when(accountRepository.findById(anyLong())).thenThrow(new RuntimeException());

    assertThrows(RuntimeException.class, () -> service.findById(ID));
  }

  @Test
  @DisplayName("findById success")
  void testCase02() {
    when(accountRepository.findById(ID)).thenReturn(ACCOUNT);

    var result = service.findById(ID);

    verify(accountRepository, times(1)).findById(ID);
    assertEquals(ACCOUNT, result);
  }

  @Test
  @DisplayName("findByUserId throws exception")
  void testCase03() {
    when(accountRepository.findByUserId(anyLong())).thenThrow(new RuntimeException());

    assertThrows(RuntimeException.class, () -> service.findByUserId(USER_ID));
  }

  @Test
  @DisplayName("findByUserId success")
  void testCase04() {
    when(accountRepository.findByUserId(USER_ID)).thenReturn(ACCOUNT_LIST);

    var result = service.findByUserId(USER_ID);

    verify(accountRepository, times(1)).findByUserId(USER_ID);
    assertEquals(ACCOUNT_LIST, result);
  }

  @Test
  @DisplayName("create throws exception")
  void testCase05() {
    when(userService.existById(any())).thenReturn(false);

    assertThrows(UserNotFoundException.class, () -> service.create(ACCOUNT));
    verify(userService, times(1)).existById(any());
  }

  @Test
  @DisplayName("create success")
  void testCase06() {
    when(userService.existById(any())).thenReturn(true);
    when(accountRepository.save(ACCOUNT)).thenReturn(ACCOUNT);

    var result = service.create(ACCOUNT);

    verify(userService, times(1)).existById(any());
    verify(accountRepository, times(1)).save(ACCOUNT);
    assertEquals(ACCOUNT, result);
  }

  @Test
  @DisplayName("update throws exception")
  void testCase07() {
    when(accountRepository.save(any(Account.class))).thenThrow(new RuntimeException());

    assertThrows(RuntimeException.class, () -> service.update(ACCOUNT));
  }

  @Test
  @DisplayName("update success")
  void testCase08() {
    when(accountRepository.save(ACCOUNT)).thenReturn(ACCOUNT);

    var result = service.update(ACCOUNT);

    verify(accountRepository, times(1)).save(ACCOUNT);
    assertEquals(ACCOUNT, result);
  }

  @Test
  @DisplayName("deleteById throws exception")
  void testCase09() {
    doThrow(new RuntimeException()).when(accountRepository).deleteById(anyLong());

    assertThrows(RuntimeException.class, () -> service.deleteById(ID));
  }

  @Test
  @DisplayName("deleteById success")
  void testCase10() {
    doNothing().when(accountRepository).deleteById(ID);

    service.deleteById(ID);

    verify(accountRepository, times(1)).deleteById(ID);
  }
}
