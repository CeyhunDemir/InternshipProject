package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Value;

public interface AddValueUseCase {
    Value addValue(AddValueRequestDTO addValueRequestDTO);
}
