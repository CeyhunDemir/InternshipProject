package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.AttributeRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Long>, AttributeRepositoryPort {
    Optional<Attribute> findByAttributeName(String name);
}
