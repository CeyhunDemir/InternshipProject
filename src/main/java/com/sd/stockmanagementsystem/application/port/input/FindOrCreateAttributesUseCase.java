package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Attribute;

import java.util.Map;
import java.util.Set;

public interface FindOrCreateAttributesUseCase {
    Map<String, Attribute> findOrCreateAttributes(Set<String> attributeNames);
}
