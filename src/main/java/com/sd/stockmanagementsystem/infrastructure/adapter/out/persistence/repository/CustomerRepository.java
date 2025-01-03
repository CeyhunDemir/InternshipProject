package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.CustomerRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryPort {

    Optional<Customer> findById(long id);

    Optional<Customer> findByName(String name);

    List<Customer> findAll();

    @Query(value = "SELECT customer FROM Customer customer WHERE LOWER(customer.name) LIKE LOWER(CONCAT('%',:subString, '%') ) ORDER BY customer.name ASC LIMIT 5")
    List<Customer> findBySubstring(@Param("subString") String substring);


}
