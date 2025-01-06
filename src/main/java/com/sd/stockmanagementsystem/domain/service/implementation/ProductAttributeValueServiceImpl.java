package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.*;
import com.sd.stockmanagementsystem.application.dto.request.*;
import com.sd.stockmanagementsystem.domain.model.*;
import com.sd.stockmanagementsystem.domain.service.*;
import com.sd.stockmanagementsystem.domain.util.StringConverter;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.ProductAttributeValueMapper;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductAttributeValueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductAttributeValueServiceImpl implements IProductAttributeValueService {
    private final IProductService productService;
    private final IAttributeValueService attributeValueService;
    private final IAttributeService attributeService;
    private final IValueService valueService;
    private final IGeneralMapperService productValueMapperService;
    private final ProductAttributeValueRepository productAttributeValueRepository;
    private final ProductAttributeValueMapper productAttributeValueMapper;

    @Override
    public ProductAttributeValue addProductAttributeValue(AddProductAttributeValueRequestDTO addProductAttributeValueRequestDTO) {
        Product product = productService.findProductByProductKey(ProductKey.builder()
                .name(addProductAttributeValueRequestDTO.getProductName())
                .build());
        Attribute attribute;
        Value value;
        AttributeValue attributeValue;
        try {
            attribute = attributeService.findAttributeByAttributeKey(AttributeKey.builder()
                    .attributeName(addProductAttributeValueRequestDTO.getAttributeName())
                    .build());
        } catch (EntityNotFoundException e) {
            attribute = attributeService.addAttribute(AddAttributeRequestDTO.builder()
                    .attributeName(addProductAttributeValueRequestDTO.getAttributeName())
                    .build());
        }
        try {
            value = valueService.findValueWithValueKey(ValueKey.builder()
                    .value(addProductAttributeValueRequestDTO.getValue())
                    .build());
        } catch (EntityNotFoundException e) {
            value = valueService.addValue(AddValueRequestDTO.builder()
                    .value(addProductAttributeValueRequestDTO.getValue())
                    .build());
        }

        try {
            attributeValue = attributeValueService.findAttributeValueByAttributeValueKey(AttributeValueKey.builder()
                    .attributeKey(productValueMapperService.forRequest().map(attribute, AttributeKey.class))
                    .valueKey(productValueMapperService.forRequest().map(value, ValueKey.class))
                    .build());
        } catch (EntityNotFoundException e) {
            attributeValue = attributeValueService.addAttributeValue(AddAttributeValueRequestDTO.builder()
                    .attributeKey(productValueMapperService.forRequest().map(attribute, AttributeKey.class))
                    .valueKey(productValueMapperService.forRequest().map(value, ValueKey.class))
                    .build());
        }
        /*ProductAttributeValue productAttributeValue = productAttributeValueMapper.toProductAttributeValue(attributeValue, product);*/
        ProductAttributeValue productAttributeValue = new ProductAttributeValue();
        productAttributeValue.setAttributeValue(attributeValue);
        productAttributeValue.setProduct(product);
        productAttributeValueRepository.save(productAttributeValue);
        extendProductName(product, attributeValue);
        return productAttributeValue;
    }

    @Override
    public ProductAttributeValue findProductAttributeValueWithProductAttributeValueKey(ProductAttributeValueKey productAttributeValueKey) throws EntityNotFoundException {
        if (productAttributeValueKey.getProductKey() == null || productAttributeValueKey.getAttributeValueKey() == null) {
            throw new EntityNotFoundException("Product or AttributeValue key is empty!");
        }
        String productName = StringConverter.formatName(productAttributeValueKey.getProductKey().getName());
        String attributeName = StringConverter.formatName(productAttributeValueKey.getAttributeValueKey().getAttributeKey().getAttributeName());
        String value = StringConverter.formatName(productAttributeValueKey.getAttributeValueKey().getValueKey().getValue());
        Long productId = productAttributeValueKey.getProductKey().getId();
        Long attributeId = productAttributeValueKey.getAttributeValueKey().getAttributeKey().getId();
        Long valueId = productAttributeValueKey.getAttributeValueKey().getValueKey().getId();
        if (productId != null && attributeId != null && valueId != null) {
            return productAttributeValueRepository
                    .findProductAttributeValueByProduct_IdAndAttributeValue_Attribute_IdAndAttributeValue_Value_Id(
                            productId, attributeId, valueId
                    ).orElseThrow(() -> new EntityNotFoundException("ProductAttributeValue not found with product id:" + productId + " , attribute id:" + attributeId + " , value id:" + valueId));
        } else if (StringUtils.hasText(productName)
                && StringUtils.hasText(attributeName)
                && StringUtils.hasText(value)) {
            return productAttributeValueRepository
                    .findProductAttributeValueByProduct_NameAndAttributeValue_Attribute_AttributeNameAndAttributeValue_Value_Value(
                            productName, attributeName, value
                    ).orElseThrow(() -> new EntityNotFoundException("ProductAttributeValue not found with product name:" + productName + " , attribute name:" + attributeName + " , value name:" + value));
        } else throw new EntityNotFoundException("Insufficent data on productAttributeValueKey!");
    }

    @Override
    public void addMultipleAttributes(AddMultipleAttributesRequestDTO addMultipleAttributesRequestDTO) {
        Product product = productService.findProductByProductKey(ProductKey.builder().id(addMultipleAttributesRequestDTO.getProductId()).build());
        Map<AttributeKeyValue, AttributeValue> attributeValue = attributeValueService.findOrCreateAttributeValue(addMultipleAttributesRequestDTO.getAttributes());
        List<ProductAttributeValue> existingProductAttributeValue = attributeValue.entrySet().stream()
                .map(entry -> productAttributeValueRepository.findProductAttributeValueByProduct_IdAndAttributeValue_Attribute_IdAndAttributeValue_Value_Id(
                        product.getId(),
                        entry.getValue().getAttribute().getId(),
                        entry.getValue().getValue().getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        Set<AttributeValue> existingAttributeValues = existingProductAttributeValue.stream()
                .map(ProductAttributeValue::getAttributeValue)
                .collect(Collectors.toSet());
        Set<AttributeValue> missingAttributeValues = attributeValue.values().stream()
                .filter(value -> !existingAttributeValues.contains(value))
                .collect(Collectors.toSet());
        List<ProductAttributeValue> newProductAttributeValues = missingAttributeValues.stream()
                .map(entry -> {
                    ProductAttributeValue productAttributeValue = new ProductAttributeValue();
                    productAttributeValue.setProduct(product);
                    productAttributeValue.setAttributeValue(entry);
                    extendProductName(product, entry);
                    return productAttributeValue;
                })
                .collect(Collectors.toList());

        productAttributeValueRepository.saveAll(newProductAttributeValues);
        /*existingProductAttributeValue.addAll(newProductAttributeValues);*/
    }

    public void extendProductName(Product product, AttributeValue attributeValue) {
        UpdateProductRequestDTO updateProductRequestDTO = productValueMapperService.forRequest().map(product, UpdateProductRequestDTO.class);
        if (!product.getName().contains("#")) {
            updateProductRequestDTO.setName(product.getName() + "#" + attributeValue.getAttribute().getAttributeName() + ":" + attributeValue.getValue().getValue());
        } else {
            updateProductRequestDTO.setName(product.getName() + "," + attributeValue.getAttribute().getAttributeName() + ":" + attributeValue.getValue().getValue());
        }
        productService.updateProduct(updateProductRequestDTO);
    }

}


