package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.domain.model.Attribute;

import java.util.Optional;

public interface FindAttributeByAttributeKey {
    Attribute findAttributeByAttributeKey(AttributeKey attributeKey);
}
