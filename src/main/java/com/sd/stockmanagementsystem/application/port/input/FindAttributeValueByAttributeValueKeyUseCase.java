package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeValueKey;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;

public interface FindAttributeValueByAttributeValueKeyUseCase {
    AttributeValue findAttributeValue(AttributeValueKey attributeValueKey);
}
