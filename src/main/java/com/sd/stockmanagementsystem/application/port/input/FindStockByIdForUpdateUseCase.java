package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Stock;

public interface FindStockByIdForUpdateUseCase {
    Stock findStockByIdForUpdate(long id);
}
