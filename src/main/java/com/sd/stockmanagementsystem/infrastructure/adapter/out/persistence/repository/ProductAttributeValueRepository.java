package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.ProductAttributeValueRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long>, ProductAttributeValueRepositoryPort {
    Optional<ProductAttributeValue> findProductAttributeValueByProduct_IdAndAttributeValue_Attribute_IdAndAttributeValue_Value_Id(Long productId, Long attributeId, Long valueId);

    Optional<ProductAttributeValue> findProductAttributeValueByProduct_NameAndAttributeValue_Attribute_AttributeNameAndAttributeValue_Value_Value(String productName, String attributeName, String value);
}
