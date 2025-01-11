package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.MoveMultipleProductsInStockRequestDTO;

public interface MoveMultipleProductsInStockUseCase {
    void moveMultipleProductsInStock(MoveMultipleProductsInStockRequestDTO moveMultipleProductsInStockRequestDTO);
}
