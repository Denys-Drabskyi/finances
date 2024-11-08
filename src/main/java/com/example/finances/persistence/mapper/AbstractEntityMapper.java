package com.example.finances.persistence.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface AbstractEntityMapper<E, M> {
    E toEntity(M model);
    List<E> toEntityList(List<M> models);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromModel(@MappingTarget E entity, M dto);

    M toModel(E entity);
    List<M> toModelList(List<E> entities);
}
