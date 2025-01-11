package com.sd.stockmanagementsystem.application.port.input;

import java.util.Map;

public interface UpdateStockQuantityInBulkUseCase {
    void updateStockQuantityInBulk(Map<Long, Double> stockQuantityMap);
}
