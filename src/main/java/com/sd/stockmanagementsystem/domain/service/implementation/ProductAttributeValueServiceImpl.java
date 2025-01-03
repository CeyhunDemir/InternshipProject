package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.*;
import com.sd.stockmanagementsystem.application.dto.request.AddMultipleAttributesRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.AddProductAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.*;
import com.sd.stockmanagementsystem.domain.service.*;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductAttributeValueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAttributeValueServiceImpl implements IProductAttributeValueService {
    private final IProductService productService;
    private final IAttributeService attributeService;
    private final IValueService valueService;
    private final IAttributeValueService attributeValueService;
    private final ProductAttributeValueRepository productAttributeValueRepository;

    @Override
    public ProductAttributeValue addProductAttributeValue(AddProductAttributeValueRequestDTO addProductAttributeValueRequestDTO) {
        Product product = productService.findProductByProductKey(ProductKey.builder()
                .name(addProductAttributeValueRequestDTO.getProductName())
                .build());
        AttributeValueKey attributeValueKey = AttributeValueKey.builder()
                .attributeKey(AttributeKey.builder().
                        attributeName(addProductAttributeValueRequestDTO.getAttributeName()).build())
                .valueKey(ValueKey.builder().
                        value(addProductAttributeValueRequestDTO.getValue()).build())
                .build();
        AttributeValue attributeValue = attributeValueService.findAttributeValue(attributeValueKey);
        ProductAttributeValue productAttributeValue = new ProductAttributeValue();
        productAttributeValue.setAttributeValue(attributeValue);
        productAttributeValue.setProduct(product);
        productAttributeValueRepository.save(productAttributeValue);
        return productAttributeValue;
    }

    @Override
    public ProductAttributeValue findProductAttributeValue(ProductAttributeValueKey productAttributeValueKey) {
        if (productAttributeValueKey.getProductKey() == null || productAttributeValueKey.getAttributeValueKey() == null) {
            throw new EntityNotFoundException("Product or AttributeValue key is empty!");
        }
        String productName = productAttributeValueKey.getProductKey().getName();
        String attributeName = productAttributeValueKey.getAttributeValueKey().getAttributeKey().getAttributeName();
        String value = productAttributeValueKey.getAttributeValueKey().getValueKey().getValue();
        Long productId = productAttributeValueKey.getProductKey().getId();
        Long attributeId = productAttributeValueKey.getAttributeValueKey().getAttributeKey().getId();
        Long valueId = productAttributeValueKey.getAttributeValueKey().getValueKey().getId();
        if (StringUtils.hasText(productName)
                && StringUtils.hasText(attributeName)
                && StringUtils.hasText(value)) {
            Optional<ProductAttributeValue> productAttributeValue = productAttributeValueRepository
                    .findProductAttributeValueByProduct_NameAndAttributeValue_Attribute_AttributeNameAndAttributeValue_Value_Value(
                            productName, attributeName, value
                    );
            if (productAttributeValue.isPresent()) {
                return productAttributeValue.get();
            }
        } else if (productId != null && attributeId != null && valueId != null) {
            Optional<ProductAttributeValue> productAttributeValue = productAttributeValueRepository
                    .findProductAttributeValueByProduct_IdAndAttributeValue_Attribute_IdAndAttributeValue_Value_Id(
                            productId, attributeId, valueId
                    );
            if (productAttributeValue.isPresent()) {
                return productAttributeValue.get();
            }
        }
        AddProductAttributeValueRequestDTO addProductAttributeValueRequestDTO = new AddProductAttributeValueRequestDTO();
        addProductAttributeValueRequestDTO.setProductName(productName);
        addProductAttributeValueRequestDTO.setAttributeName(attributeName);
        addProductAttributeValueRequestDTO.setValue(value);
        return addProductAttributeValue(addProductAttributeValueRequestDTO);
    }

    @Override
    public void addMultipleAttributes(AddMultipleAttributesRequestDTO addMultipleAttributesRequestDTO) {
        addMultipleAttributesRequestDTO.getAttributes().entrySet().stream()
                .forEach(entry ->
                        this.addProductAttributeValue(AddProductAttributeValueRequestDTO.builder()
                                .productName(addMultipleAttributesRequestDTO.getProductName())
                                .attributeName(entry.getKey())
                                .value(entry.getValue())
                                .build()
                        ));

    }
}
