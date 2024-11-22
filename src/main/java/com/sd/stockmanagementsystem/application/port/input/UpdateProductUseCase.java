package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;

public interface UpdateProductUseCase {
    void updateProduct(UpdateProductRequestDTO updateProductRequestDTO);
}
