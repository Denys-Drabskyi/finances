package com.example.finances.persistence.mapper;

import org.mapstruct.Context;

import java.util.List;

public interface AbstractEntityMapper<E, M> {
    E toEntity(M model, @Context CycleAvoidingMappingContext context);
    List<E> toEntityList(List<M> models, @Context CycleAvoidingMappingContext context);

    M toModel(E entity, @Context CycleAvoidingMappingContext context);
    List<M> toModelList(List<E> entities, @Context CycleAvoidingMappingContext context);

    // todo hide/remove CycleAvoidingMappingContext
//    default E toEntity(M model) {
//        return toEntity(model, new CycleAvoidingMappingContext());
//    }
//
//    default M toModel(E entity) {
//        return toModel(entity, new CycleAvoidingMappingContext());
//    }
}
