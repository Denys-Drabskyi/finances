package com.example.finances.presentation.rest.mapper;

import com.example.finances.domain.model.Account;
import com.example.finances.presentation.rest.dto.AccountDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper extends AbstractDtoMapper<Account, AccountDto> {}
