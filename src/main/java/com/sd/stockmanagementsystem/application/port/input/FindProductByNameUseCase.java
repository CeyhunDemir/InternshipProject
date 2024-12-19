package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Product;

public interface FindProductByNameUseCase {
    Product findProductByName(String name);
}
