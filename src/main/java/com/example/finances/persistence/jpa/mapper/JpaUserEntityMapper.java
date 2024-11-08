package com.example.finances.persistence.jpa.mapper;

import com.example.finances.domain.model.User;
import com.example.finances.persistence.jpa.entity.JpaUserEntity;
import com.example.finances.persistence.mapper.AbstractEntityMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaUserEntityMapper extends AbstractEntityMapper<JpaUserEntity, User> {}
