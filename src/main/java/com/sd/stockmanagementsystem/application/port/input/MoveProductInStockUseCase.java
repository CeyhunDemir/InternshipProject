package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.MoveProductInStockRequestDTO;

public interface MoveProductInStockUseCase {
    void MoveProductInStock(MoveProductInStockRequestDTO moveProductInStockRequestDTO);
}
