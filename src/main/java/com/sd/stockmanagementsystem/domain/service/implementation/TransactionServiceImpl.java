package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.model.Transaction;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final IGeneralMapperService transactionMapperService;
    private final IProductService productService;
    private final ICustomerService customerService;
    @Override
    public void addTransaction(AddTransactionRequestDTO addTransactionRequestDTO) {
        Transaction transaction = Transaction.builder()
                .product(productService.getProductById(addTransactionRequestDTO.getProduct_id()))
                .customer(customerService.getCustomerById(addTransactionRequestDTO.getCustomer_id()))
                .quantity(addTransactionRequestDTO.getQuantity())
                .transactionType(addTransactionRequestDTO.getTransactionType())
                .totalPrice(addTransactionRequestDTO.getTotalPrice())
                .build();
        transactionRepository.save(transaction);
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
