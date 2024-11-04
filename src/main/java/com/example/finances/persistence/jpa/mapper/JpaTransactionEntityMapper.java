package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.Transaction;
import com.example.finances.persistence.jpa.entity.JpaTransactionEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;
import com.example.finances.persistence.mapper.CycleAvoidingMappingContext;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class JpaTransactionEntityMapper implements AbstractEntityMapper<JpaTransactionEntity, Transaction> {

    @Lazy
    @Autowired
    protected JpaAccountEntityMapper accountEntityMapper;

    @Override
    @Mapping(target = "account", expression = "java(accountEntityMapper.toEntity(model.getAccount(), context))")
    public abstract JpaTransactionEntity toEntity(Transaction model, @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "account", expression = "java(accountEntityMapper.toModel(entity.getAccount(), context))")
    public abstract Transaction toModel(JpaTransactionEntity entity, @Context CycleAvoidingMappingContext context);
}
