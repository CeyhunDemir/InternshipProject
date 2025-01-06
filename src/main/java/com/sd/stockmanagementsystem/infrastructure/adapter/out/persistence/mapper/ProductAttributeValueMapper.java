package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;

import com.sd.stockmanagementsystem.application.dto.request.AddProductAttributeValueRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAttributeValueMapper {
    AddProductAttributeValueRequestDTO toAddProductAttributeValueRequestDTO(String productName, String attributeName, String value);

    /*@Mapping(target = "id", ignore = true) // Ignore the 'id' field in ProductAttributeValue
    @Mapping(target = "attributeValue", ignore = true) // Let custom logic handle attributeValue
    @Mapping(target = "product", ignore = true) // Let custom logic handle product
    ProductAttributeValue toProductAttributeValue(AttributeValue attributeValue, Product product);

    @AfterMapping
    default void setNestedObjects(@MappingTarget ProductAttributeValue productAttributeValue, AttributeValue attributeValue, Product product) {
        productAttributeValue.setAttributeValue(attributeValue);
        productAttributeValue.setProduct(product);
    }*/
}
