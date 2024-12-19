package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.TransactionFilterDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllTransactionsResponseDTO;

import java.util.List;

public interface GetAllTransactionsWithFiltersUseCase {
    List<GetAllTransactionsResponseDTO> getAllTransactionsWithFilters(TransactionFilterDTO transactionFilterDTO);
}
