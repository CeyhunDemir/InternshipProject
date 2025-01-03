package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;

import java.util.List;

public interface AddMultipleTransactionsUseCase {
    void addMultipleTransactions(List<AddTransactionRequestDTO> addTransactionRequestDTOList);
}
