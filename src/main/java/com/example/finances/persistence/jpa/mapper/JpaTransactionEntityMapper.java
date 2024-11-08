package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.Transaction;
import com.example.finances.persistence.jpa.entity.JpaTransactionEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaTransactionEntityMapper extends AbstractEntityMapper<JpaTransactionEntity, Transaction> {}
