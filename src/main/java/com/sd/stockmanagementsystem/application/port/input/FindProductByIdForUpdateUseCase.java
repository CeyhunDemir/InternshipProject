package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Product;

public interface FindProductByIdForUpdateUseCase {
    Product findProductByIdForUpdate(Long id);
}
