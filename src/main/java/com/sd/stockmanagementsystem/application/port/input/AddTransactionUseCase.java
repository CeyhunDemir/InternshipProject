package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;

public interface AddTransactionUseCase {
    void addTransaction(AddTransactionRequestDTO addTransactionRequestDTO);
}
