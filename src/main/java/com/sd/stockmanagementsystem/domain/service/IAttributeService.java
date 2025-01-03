package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.port.input.*;

public interface IAttributeService extends
        AddAttributeUseCase,
        DeleteAttributeUseCase,
        FindAttributeByAttributeKey,
        UpdateAttributeUseCase {
}
