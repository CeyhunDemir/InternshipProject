package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;
import org.modelmapper.ModelMapper;
public interface IProductMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
