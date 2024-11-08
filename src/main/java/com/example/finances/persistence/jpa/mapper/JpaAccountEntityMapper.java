package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.Account;
import com.example.finances.persistence.jpa.entity.JpaAccountEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaAccountEntityMapper extends AbstractEntityMapper<JpaAccountEntity, Account> {}
