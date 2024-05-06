package org.example.customerstores.mapper.Impl;

import org.example.customerstores.mapper.Mapper;
import org.example.customerstores.model.UnfinishedOrder;
import org.example.customerstores.model.dto.UnfinishedOrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UnfinishedOrderMapper implements Mapper<UnfinishedOrder, UnfinishedOrderDto> {
    private final ModelMapper modelMapper;

    public UnfinishedOrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UnfinishedOrderDto toDto(UnfinishedOrder unfinishedOrder) {
        return modelMapper.map(unfinishedOrder, UnfinishedOrderDto.class);
    }

    @Override
    public UnfinishedOrder toModel(UnfinishedOrderDto urgentOrderDto) {
        return modelMapper.map(urgentOrderDto, UnfinishedOrder.class);
    }
}
