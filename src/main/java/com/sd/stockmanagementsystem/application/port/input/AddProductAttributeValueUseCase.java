package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddProductAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.ProductAttributeValue;

public interface AddProductAttributeValueUseCase {
    ProductAttributeValue addProductAttributeValue(AddProductAttributeValueRequestDTO addProductAttributeValueRequestDTO);
}
