package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;


import com.sd.stockmanagementsystem.application.dto.request.AddMultipleTransactionsRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.TransactionFilterDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllTransactionsResponseDTO;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final ITransactionService transactionService;

    @PostMapping
    public void addTransaction(@RequestBody @Valid AddTransactionRequestDTO addTransactionRequestDTO) {
        transactionService.addTransaction(addTransactionRequestDTO);
    }

    @PostMapping("/multiple")
    public void addMultipleTransactions(@RequestBody @Valid AddMultipleTransactionsRequestDTO addMultipleTransactionsRequestDTO) {
        transactionService.addMultipleTransactions(addMultipleTransactionsRequestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetAllTransactionsResponseDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    @PostMapping("/allWithFilters")
    public ResponseEntity<List<GetAllTransactionsResponseDTO>> getAllTransactionsWithFilters(@RequestBody TransactionFilterDTO transactionFilterDTO) {
        return ResponseEntity.ok(transactionService.getAllTransactionsWithFilters(transactionFilterDTO));
    }
}
