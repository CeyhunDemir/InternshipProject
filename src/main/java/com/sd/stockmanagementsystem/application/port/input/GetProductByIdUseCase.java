package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetProductByIdResponseDTO;

public interface GetProductByIdUseCase {
    GetProductByIdResponseDTO getProductById(long id);
}
