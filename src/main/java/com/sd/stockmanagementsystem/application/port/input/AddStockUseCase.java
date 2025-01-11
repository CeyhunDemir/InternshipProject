package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddStockRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Stock;

public interface AddStockUseCase {
    Stock addStock(AddStockRequestDTO addStockRequestDTO);
}
