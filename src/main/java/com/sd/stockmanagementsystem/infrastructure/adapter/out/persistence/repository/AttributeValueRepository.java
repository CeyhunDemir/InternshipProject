package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.AttributeValueRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long>, AttributeValueRepositoryPort {
    Optional<AttributeValue> findByAttribute_Id(Long attributeId);

    Optional<AttributeValue> findByValue_Id(Long valueId);

    Optional<AttributeValue> findByAttribute_IdAndValue_Id(Long attributeId, Long valueId);

    Optional<AttributeValue> findByAttribute_AttributeNameAndValue_Value(String attributeName, String value);
}
