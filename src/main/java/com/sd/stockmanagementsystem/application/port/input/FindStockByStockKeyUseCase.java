package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.StockKey;
import com.sd.stockmanagementsystem.domain.model.Stock;

public interface FindStockByStockKeyUseCase {
    Stock findStockByStockKey(StockKey stockKey);
}
