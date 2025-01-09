package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.StockKey;

public interface FindQuantityInStockByStockKeyUseCase {
    Double findQuantityInStockByStockKey(StockKey stockKey);
}
