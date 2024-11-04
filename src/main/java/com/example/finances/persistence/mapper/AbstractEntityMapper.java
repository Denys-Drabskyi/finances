package com.example.finances.persistence.mapper;

import org.mapstruct.Context;
import org.mapstruct.Named;

import java.util.List;

public interface AbstractEntityMapper<E, M> {
    E toEntity(M model, @Context CycleAvoidingMappingContext context);
    List<E> toEntityList(List<M> models, @Context CycleAvoidingMappingContext context);

    M toModel(E entity, @Context CycleAvoidingMappingContext context);
    List<M> toModelList(List<E> entities, @Context CycleAvoidingMappingContext context);

    @Named("ignore")
    default E toEntity(M model) {
        return toEntity(model, new CycleAvoidingMappingContext());
    }

    @Named("ignore")
    default List<E> toEntitylList(List<M> models) {
        return toEntityList(models, new CycleAvoidingMappingContext());
    }

    @Named("ignore")
    default M toModel(E entity) {
        return toModel(entity, new CycleAvoidingMappingContext());
    }

    @Named("ignore")
    default List<M> toModelList(List<E> entities) {
        return toModelList(entities, new CycleAvoidingMappingContext());
    }

}
