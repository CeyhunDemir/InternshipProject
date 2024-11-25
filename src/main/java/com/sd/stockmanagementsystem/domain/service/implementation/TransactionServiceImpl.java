package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.model.Transaction;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;
    @Override
    public void addTransaction(AddTransactionRequestDTO addTransactionRequestDTO) {

    }
    @Transactional
    @Override
    public double calculateQuantity(long id) {
        List<Transaction> transactions = transactionRepository.findByProduct_Id(id);
        double totalQuantity = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionEnumeration.TransactionType.SELL)
                totalQuantity -= transaction.getQuantity();
            else
                totalQuantity += transaction.getQuantity();
        }
        return totalQuantity;
    }
}
