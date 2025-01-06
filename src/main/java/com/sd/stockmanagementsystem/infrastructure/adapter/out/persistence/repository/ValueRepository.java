package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.domain.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ValueRepository extends JpaRepository<Value, Long> {
    Optional<Value> findByValue(String value);

    List<Value> findByValueIn(Set<String> values);
}
