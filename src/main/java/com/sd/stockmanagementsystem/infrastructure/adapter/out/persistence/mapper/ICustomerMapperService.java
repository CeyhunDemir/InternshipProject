package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;

import org.modelmapper.ModelMapper;

public interface ICustomerMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
