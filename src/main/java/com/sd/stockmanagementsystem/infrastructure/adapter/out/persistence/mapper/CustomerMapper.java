package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;


import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    void updateCustomerFromDTO(UpdateCustomerRequestDTO updateCustomerRequestDTO, @MappingTarget Customer customer);

}
