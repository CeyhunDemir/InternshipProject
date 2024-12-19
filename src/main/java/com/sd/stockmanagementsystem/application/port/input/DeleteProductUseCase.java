package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.DeleteProductRequestDTO;

public interface DeleteProductUseCase {
    void deleteProduct(long id);
}
