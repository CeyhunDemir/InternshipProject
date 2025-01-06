package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.AddProductResponseDTO;

public interface AddProductUseCase {
    AddProductResponseDTO addProduct(AddProductRequestDTO addProductRequestDto);
}
