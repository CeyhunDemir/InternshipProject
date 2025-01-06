package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeValueKey;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import jakarta.persistence.EntityNotFoundException;

public interface FindAttributeValueByAttributeValueKeyUseCase {
    AttributeValue findAttributeValueByAttributeValueKey(AttributeValueKey attributeValueKey) throws EntityNotFoundException;
}
