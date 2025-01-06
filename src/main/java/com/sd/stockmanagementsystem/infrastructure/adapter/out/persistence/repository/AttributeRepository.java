package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.AttributeRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AttributeRepository extends JpaRepository<Attribute, Long>, AttributeRepositoryPort {
    Optional<Attribute> findByAttributeName(String name);

    List<Attribute> findByAttributeNameIn(Set<String> names);
}
