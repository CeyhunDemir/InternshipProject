package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddMultipleTransactionsUseCase;
import com.sd.stockmanagementsystem.application.port.input.AddTransactionUseCase;
import com.sd.stockmanagementsystem.application.port.input.GetAllTransactionsUseCase;
import com.sd.stockmanagementsystem.application.port.input.GetAllTransactionsWithFiltersUseCase;

public interface ITransactionService extends
        AddTransactionUseCase,
        AddMultipleTransactionsUseCase,
        GetAllTransactionsUseCase,
        GetAllTransactionsWithFiltersUseCase {
}
