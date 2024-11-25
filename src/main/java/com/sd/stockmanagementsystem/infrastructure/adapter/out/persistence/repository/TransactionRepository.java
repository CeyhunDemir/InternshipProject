package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.TransactionRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>, TransactionRepositoryPort {
    List<Transaction> findByProduct_Id(long productId);
}
