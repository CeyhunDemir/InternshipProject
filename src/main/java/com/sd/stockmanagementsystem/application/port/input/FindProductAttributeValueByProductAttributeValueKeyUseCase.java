package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.ProductAttributeValueKey;
import com.sd.stockmanagementsystem.domain.model.ProductAttributeValue;

public interface FindProductAttributeValueByProductAttributeValueKeyUseCase {
    ProductAttributeValue findProductAttributeValue(ProductAttributeValueKey productAttributeValueKey);
}
