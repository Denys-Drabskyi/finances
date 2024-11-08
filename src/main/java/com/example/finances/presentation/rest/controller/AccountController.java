package com.example.finances.presentation.rest.controller;

import com.example.finances.domain.service.AccountService;
import com.example.finances.presentation.rest.dto.AccountDto;
import com.example.finances.presentation.rest.dto.CreateAccountDto;
import com.example.finances.presentation.rest.mapper.AccountDtoMapper;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService service;
  private final AccountDtoMapper mapper;

  @GetMapping("/{id}")
  public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
    var account = service.findById(id);
    return ResponseEntity.ok(mapper.toDto(account));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<AccountDto>> getUserAccounts(@PathVariable Long userId) {
    var accounts = service.findByUserId(userId);
    return ResponseEntity.ok(mapper.toDtoList(accounts));
  }

  @PostMapping
  public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CreateAccountDto dto) {
    var account = mapper.toModel(dto);
    account = service.create(account);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(account));
  }

  @PutMapping
  public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid AccountDto dto) {
    var account = mapper.toModel(dto);
    account = service.update(account);
    return ResponseEntity.ok(mapper.toDto(account));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
