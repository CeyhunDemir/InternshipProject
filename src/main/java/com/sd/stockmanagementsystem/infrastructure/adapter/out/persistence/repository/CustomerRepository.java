package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.CustomerRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerRepositoryPort {
    Optional<Customer> findById(long id);
}
