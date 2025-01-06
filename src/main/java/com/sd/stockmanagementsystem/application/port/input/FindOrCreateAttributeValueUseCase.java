package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKeyValue;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;

import java.util.Map;
import java.util.Set;

public interface FindOrCreateAttributeValueUseCase {
    Map<AttributeKeyValue, AttributeValue> findOrCreateAttributeValue(Set<AttributeKeyValue> attributes);
}
