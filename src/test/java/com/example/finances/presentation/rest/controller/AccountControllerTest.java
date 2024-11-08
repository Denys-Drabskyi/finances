package com.example.finances.presentation.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.finances.domain.model.Account;
import com.example.finances.domain.service.AccountService;

import com.example.finances.presentation.rest.dto.AccountDto;
import com.example.finances.presentation.rest.dto.CreateAccountDto;
import com.example.finances.presentation.rest.mapper.AccountDtoMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private AccountService service;

    @Mock
    private AccountDtoMapper mapper;

    @InjectMocks
    private AccountController controller;

    private static final Long ID = 1L;
    private static final Long USER_ID = 2L;
    private static final AccountDto ACCOUNT_DTO = AccountDto.builder().build();
    private static final CreateAccountDto CREATE_ACCOUNT_DTO = CreateAccountDto.builder().build();
    private static final Account ACCOUNT = Account.builder().build();
    private static final List<Account> ACCOUNT_LIST = List.of(ACCOUNT);
    private static final List<AccountDto> ACCOUNT_DTO_LIST = List.of(ACCOUNT_DTO);

    @Test
    @DisplayName("getById throws exception")
    void testCase01() {
        when(service.findById(anyLong())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> controller.getById(ID));
    }

    @Test
    @DisplayName("getById success")
    void testCase02() {
        when(service.findById(ID)).thenReturn(ACCOUNT);
        when(mapper.toDto(ACCOUNT)).thenReturn(ACCOUNT_DTO);

        var rez = controller.getById(ID);

        verify(service, times(1)).findById(ID);
        verify(mapper, times(1)).toDto(ACCOUNT);
        assertEquals(ResponseEntity.ok(ACCOUNT_DTO), rez);
    }

    @Test
    @DisplayName("getUserAccounts throws exception")
    void testCase03() {
        when(service.findByUserId(anyLong())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> controller.getUserAccounts(USER_ID));
    }

    @Test
    @DisplayName("getUserAccounts success")
    void testCase04() {
        when(service.findByUserId(USER_ID)).thenReturn(ACCOUNT_LIST);
        when(mapper.toDtoList(ACCOUNT_LIST)).thenReturn(ACCOUNT_DTO_LIST);

        var rez = controller.getUserAccounts(USER_ID);

        verify(service, times(1)).findByUserId(USER_ID);
        verify(mapper, times(1)).toDtoList(ACCOUNT_LIST);
        assertEquals(ResponseEntity.ok(ACCOUNT_DTO_LIST), rez);
    }

    @Test
    @DisplayName("createAccount throws exception")
    void testCase05() {
        when(mapper.toModel(CREATE_ACCOUNT_DTO)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> controller.createAccount(CREATE_ACCOUNT_DTO));
    }

    @Test
    @DisplayName("createAccount success")
    void testCase06() {
        when(mapper.toModel(CREATE_ACCOUNT_DTO)).thenReturn(ACCOUNT);
        when(service.create(ACCOUNT)).thenReturn(ACCOUNT);
        when(mapper.toDto(ACCOUNT)).thenReturn(ACCOUNT_DTO);

        var rez = controller.createAccount(CREATE_ACCOUNT_DTO);

        verify(mapper, times(1)).toModel(CREATE_ACCOUNT_DTO);
        verify(service, times(1)).create(ACCOUNT);
        verify(mapper, times(1)).toDto(ACCOUNT);
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(ACCOUNT_DTO), rez);
    }

    @Test
    @DisplayName("updateAccount throws exception")
    void testCase07() {
        when(mapper.toModel(ACCOUNT_DTO)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> controller.updateAccount(ACCOUNT_DTO));
    }

    @Test
    @DisplayName("updateAccount success")
    void testCase08() {
        when(mapper.toModel(ACCOUNT_DTO)).thenReturn(ACCOUNT);
        when(service.update(ACCOUNT)).thenReturn(ACCOUNT);
        when(mapper.toDto(ACCOUNT)).thenReturn(ACCOUNT_DTO);

        var rez = controller.updateAccount(ACCOUNT_DTO);

        verify(mapper, times(1)).toModel(ACCOUNT_DTO);
        verify(service, times(1)).update(ACCOUNT);
        verify(mapper, times(1)).toDto(ACCOUNT);
        assertEquals(ResponseEntity.ok(ACCOUNT_DTO), rez);
    }

    @Test
    @DisplayName("deleteById throws exception")
    void testCase09() {
        doThrow(new RuntimeException()).when(service).deleteById(anyLong());

        assertThrows(RuntimeException.class, () -> controller.deleteById(ID));
    }

    @Test
    @DisplayName("deleteById success")
    void testCase10() {
        doNothing().when(service).deleteById(ID);

        var rez = controller.deleteById(ID);

        verify(service, times(1)).deleteById(ID);
        assertEquals(ResponseEntity.noContent().build(), rez);
    }
}

