package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddMultipleAttributesUseCase;
import com.sd.stockmanagementsystem.application.port.input.AddProductAttributeValueUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindProductAttributeValueByProductAttributeValueKeyUseCase;

public interface IProductAttributeValueService extends
        AddProductAttributeValueUseCase,
        FindProductAttributeValueByProductAttributeValueKeyUseCase,
        AddMultipleAttributesUseCase {
}
