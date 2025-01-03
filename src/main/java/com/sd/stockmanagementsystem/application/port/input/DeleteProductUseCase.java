package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.ProductKey;

public interface DeleteProductUseCase {
    void deleteProduct(ProductKey productKey);
}
