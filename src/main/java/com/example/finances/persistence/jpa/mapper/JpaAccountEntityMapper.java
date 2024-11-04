package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.Account;
import com.example.finances.persistence.jpa.entity.JpaAccountEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;
import com.example.finances.persistence.mapper.CycleAvoidingMappingContext;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class JpaAccountEntityMapper implements AbstractEntityMapper<JpaAccountEntity, Account> {

    @Lazy
    @Autowired
    protected JpaUserEntityMapper userEntityMapper;

    @Lazy
    @Autowired
    protected JpaTransactionEntityMapper transactionEntityMapper;

    @Override
    @Mapping(target = "user", expression = "java(userEntityMapper.toEntity(model.getUser(), context))")
    @Mapping(target = "transactions", expression = "java(transactionEntityMapper.toEntityList(model.getTransactions(), context))")
    public abstract JpaAccountEntity toEntity(Account model, @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "user", expression = "java(userEntityMapper.toModel(entity.getUser(), context))")
    @Mapping(target = "transactions", expression = "java(transactionEntityMapper.toModelList(entity.getTransactions(), context))")
    public abstract Account toModel(JpaAccountEntity entity, @Context CycleAvoidingMappingContext context);
}
