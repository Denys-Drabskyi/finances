package com.example.finances.presentation.rest.mapper;

public interface AbstractDtoMapper<M, D>  {
    M toModel(D dto);
    D toDto(M model);
}
