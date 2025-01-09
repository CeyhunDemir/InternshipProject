package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.FindQuantityInStockByStockKeyUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindStockByStockKeyUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindStocksByStockKeysUseCase;

public interface IStockService extends
        FindQuantityInStockByStockKeyUseCase,
        FindStockByStockKeyUseCase,
        FindStocksByStockKeysUseCase {
}
