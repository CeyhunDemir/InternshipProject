package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.request.UpdateAttributeRequestDTO;

public interface UpdateAttributeUseCase {
    void updateAttribute(UpdateAttributeRequestDTO updateAttributeRequestDTO);
}
