package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsResponseDTO;

import java.util.List;

public interface GetAllProductsUseCase {
    List<GetAllProductsResponseDTO> getAllProducts();
}
