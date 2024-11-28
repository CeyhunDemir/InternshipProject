package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;
import org.modelmapper.ModelMapper;
public interface IGeneralMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
