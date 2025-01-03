package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.*;

public interface ITransactionService extends
        AddTransactionByProductNameUseCase,
        AddTransactionByBarcodeUseCase,
        AddMultipleTransactionsUseCase,
        GetAllTransactionsUseCase,
        GetAllTransactionsWithFiltersUseCase {
}
