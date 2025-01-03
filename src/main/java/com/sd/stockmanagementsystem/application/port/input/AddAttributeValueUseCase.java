package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;

public interface AddAttributeValueUseCase {
    AttributeValue addAttributeValue(AddAttributeValueRequestDTO addAttributeValueRequestDTO);
}
