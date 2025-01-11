package com.sd.stockmanagementsystem.application.port.input;

import java.util.Map;

public interface UpdateProductQuantityInBulkUseCase {
    void updateProductQuantityInBulk(Map<Long, Double> productQuantityMap);
}
