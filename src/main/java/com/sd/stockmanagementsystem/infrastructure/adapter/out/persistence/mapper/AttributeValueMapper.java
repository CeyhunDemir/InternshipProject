package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
import com.sd.stockmanagementsystem.application.dto.request.AddAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import com.sd.stockmanagementsystem.domain.model.Value;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeValueMapper {

    @Mapping(target = "id", ignore = true) // Ignore the 'id' field in AttributeValue
    @Mapping(target = "attribute", ignore = true) // Let the custom logic handle this
    @Mapping(target = "value", ignore = true)
        // Let the custom logic handle this
    AttributeValue toAttributeValue(Attribute attribute, Value value);

    @AfterMapping
    default void setNestedObjects(@MappingTarget AttributeValue attributeValue, Attribute attribute, Value value) {
        attributeValue.setAttribute(attribute);
        attributeValue.setValue(value);
    }

    AddAttributeValueRequestDTO toAddAttributeValueRequestDTO(AttributeKey attributeKey, ValueKey valueKey);
}
