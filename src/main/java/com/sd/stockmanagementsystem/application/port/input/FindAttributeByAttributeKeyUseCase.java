package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import jakarta.persistence.EntityNotFoundException;

public interface FindAttributeByAttributeKeyUseCase {
    Attribute findAttributeByAttributeKey(AttributeKey attributeKey) throws EntityNotFoundException;
}
