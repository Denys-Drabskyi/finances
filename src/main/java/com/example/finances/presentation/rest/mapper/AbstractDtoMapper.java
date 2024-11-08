package com.example.finances.presentation.rest.mapper;

import java.util.List;

public interface AbstractDtoMapper<M, D>  {
    M toModel(D dto);
    D toDto(M model);
    List<D> toDtoList(List<M> models);
}
