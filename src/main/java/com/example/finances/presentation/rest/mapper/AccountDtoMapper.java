package com.example.finances.presentation.rest.mapper;

import com.example.finances.domain.model.Account;
import com.example.finances.presentation.rest.dto.AccountDto;
import com.example.finances.presentation.rest.dto.CreateAccountDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper extends AbstractDtoMapper<Account, AccountDto> {

    @Override
    @Mapping(target = "balance", ignore = true)
    Account toModel(AccountDto dto);

    Account toModel(CreateAccountDto dto);
}
