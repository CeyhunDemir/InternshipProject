package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;

import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    void updateProductFromDTO(UpdateProductRequestDTO updateProductRequestDTO, @MappingTarget Product product);
}
