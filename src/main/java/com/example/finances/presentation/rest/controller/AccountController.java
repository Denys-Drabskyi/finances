package com.example.finances.presentation.rest.controller;

import com.example.finances.domain.service.AccountService;
import com.example.finances.presentation.rest.dto.AccountDto;
import com.example.finances.presentation.rest.mapper.AccountDtoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
  protected final AccountService service;
  protected final AccountDtoMapper mapper;

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<AccountDto>> getAll(@PathVariable Long userId) {
    var authors = service.findByUserId(userId).stream()
        .map(mapper::toDto)
        .toList();
    return ResponseEntity.ok(authors);
  }
}
