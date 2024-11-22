package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;

public interface AddProductUseCase {
    void addProduct(AddProductRequestDTO addProductRequestDto);
}
