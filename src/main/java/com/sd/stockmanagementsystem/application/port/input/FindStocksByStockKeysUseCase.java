package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.StockKey;
import com.sd.stockmanagementsystem.domain.model.Stock;

import java.util.Map;
import java.util.Set;

public interface FindStocksByStockKeysUseCase {
    Map<StockKey, Stock> findStocksByStockKeys(Set<StockKey> stockKeys);
}
