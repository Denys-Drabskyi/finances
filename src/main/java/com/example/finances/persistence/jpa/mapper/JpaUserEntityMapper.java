package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.User;
import com.example.finances.persistence.jpa.entity.JpaUserEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;
import com.example.finances.persistence.mapper.CycleAvoidingMappingContext;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class JpaUserEntityMapper implements AbstractEntityMapper<JpaUserEntity, User> {

    @Lazy
    @Autowired
    protected JpaAccountEntityMapper accountEntityMapper;

    @Override
    @Mapping(target = "accounts", expression = "java(accountEntityMapper.toEntityList(model.getAccounts(), context))")
    public abstract JpaUserEntity toEntity(User model, @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "accounts", expression = "java(accountEntityMapper.toModelList(entity.getAccounts(), context))")
    public abstract User toModel(JpaUserEntity entity, @Context CycleAvoidingMappingContext context);
}
