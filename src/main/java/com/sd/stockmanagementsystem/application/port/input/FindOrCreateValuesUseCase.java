package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Value;

import java.util.Map;
import java.util.Set;

public interface FindOrCreateValuesUseCase {
    Map<String, Value> findOrCreateValues(Set<String> valueValues);
}
