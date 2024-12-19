package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetAllTransactionsResponseDTO;
import com.sd.stockmanagementsystem.domain.model.Transaction;

import java.util.List;

public interface GetAllTransactionsUseCase {
    List<GetAllTransactionsResponseDTO> getAllTransactions();
}
