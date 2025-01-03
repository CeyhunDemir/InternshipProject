package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.domain.model.Product;

public interface FindProductByProductKeyUseCase {
    Product findProductByProductKey(ProductKey productKey);
}
