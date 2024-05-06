package org.example.customerstores.mapper;

public interface Mapper<M, D> {

    D toDto(M m);

    M toModel(D d);

}
