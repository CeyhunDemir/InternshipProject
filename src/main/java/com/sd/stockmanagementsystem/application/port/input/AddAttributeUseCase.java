package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddAttributeRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;

public interface AddAttributeUseCase {
    Attribute addAttribute(AddAttributeRequestDTO addAttributeRequestDTO);
}
