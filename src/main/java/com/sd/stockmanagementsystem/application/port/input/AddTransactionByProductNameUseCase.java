package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionByProductNameRequestDTO;

public interface AddTransactionByProductNameUseCase {
    void addTransactionByProductName(AddTransactionByProductNameRequestDTO addTransactionByProductNameRequestDTO);
}
