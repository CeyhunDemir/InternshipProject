package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.ProductRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryPort {
    Optional<Product> findById(long id);
}
