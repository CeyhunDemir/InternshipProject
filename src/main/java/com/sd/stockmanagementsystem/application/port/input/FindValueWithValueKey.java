package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
import com.sd.stockmanagementsystem.domain.model.Value;
import jakarta.persistence.EntityNotFoundException;

public interface FindValueWithValueKey {
    Value findValueWithValueKey(ValueKey valueKey) throws EntityNotFoundException;
}
