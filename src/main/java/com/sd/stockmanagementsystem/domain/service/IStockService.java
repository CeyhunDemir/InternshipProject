package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.*;

public interface IStockService extends
        AddStockUseCase,
        FindQuantityInStockByStockKeyUseCase,
        FindStockByStockKeyUseCase,
        FindStockByIdForUpdateUseCase,
        FindStocksByStockKeysUseCase,
        UpdateStockQuantityInBulkUseCase,
        MoveProductInStockUseCase,
        MoveMultipleProductsInStockUseCase {
}
