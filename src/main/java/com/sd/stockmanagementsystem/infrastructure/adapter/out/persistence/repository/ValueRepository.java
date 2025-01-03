package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.domain.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValueRepository extends JpaRepository<Value, Long> {
    Optional<Value> findByValue(String value);
}
