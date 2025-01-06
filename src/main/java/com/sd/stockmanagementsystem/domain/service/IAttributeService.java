package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.*;

public interface IAttributeService extends
        AddAttributeUseCase,
        DeleteAttributeUseCase,
        FindAttributeByAttributeKeyUseCase,
        UpdateAttributeUseCase,
        FindOrCreateAttributesUseCase {
}
