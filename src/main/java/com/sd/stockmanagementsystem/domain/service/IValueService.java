package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddValueUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindValueWithValueKey;

public interface IValueService extends
        AddValueUseCase,
        FindValueWithValueKey {
}
