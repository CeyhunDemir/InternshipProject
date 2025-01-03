package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionByBarcodeRequestDTO;

public interface AddTransactionByBarcodeUseCase {
    void addTransactionByBarcode(AddTransactionByBarcodeRequestDTO addTransactionByBarcodeRequestDTO);
}
