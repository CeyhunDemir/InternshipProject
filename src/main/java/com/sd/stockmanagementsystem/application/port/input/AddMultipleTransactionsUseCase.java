package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddMultipleTransactionsRequestDTO;

public interface AddMultipleTransactionsUseCase {
    void addMultipleTransactions(AddMultipleTransactionsRequestDTO addMultipleTransactionsRequestDTO);
}
