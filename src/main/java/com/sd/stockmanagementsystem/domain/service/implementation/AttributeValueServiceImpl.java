package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKeyValue;
import com.sd.stockmanagementsystem.application.dto.core.AttributeValueKey;
import com.sd.stockmanagementsystem.application.dto.request.AddAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import com.sd.stockmanagementsystem.domain.model.Value;
import com.sd.stockmanagementsystem.domain.service.IAttributeService;
import com.sd.stockmanagementsystem.domain.service.IAttributeValueService;
import com.sd.stockmanagementsystem.domain.service.IValueService;
import com.sd.stockmanagementsystem.domain.util.StringConverter;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.AttributeValueMapper;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.AttributeValueRepository;
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
public class AttributeValueServiceImpl implements IAttributeValueService {
    private final IAttributeService attributeService;
    private final IValueService valueService;
    private final IGeneralMapperService attributeValueMapperService;
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeValueMapper attributeValueMapper;

    @Override
    public AttributeValue addAttributeValue(AddAttributeValueRequestDTO addAttributeValueRequestDTO) {
        Attribute attribute = attributeValueMapperService.forRequest().map(addAttributeValueRequestDTO.getAttributeKey(), Attribute.class);
        Value value = attributeValueMapperService.forRequest().map(addAttributeValueRequestDTO.getValueKey(), Value.class);
        AttributeValue attributeValue = attributeValueMapper.toAttributeValue(attribute, value);
        attributeValueRepository.save(attributeValue);
        return attributeValue;
    }


    @Override
    public AttributeValue findAttributeValueByAttributeValueKey(AttributeValueKey attributeValueKey) throws EntityNotFoundException {
        if (attributeValueKey.getValueKey() == null || attributeValueKey.getAttributeKey() == null) {
            throw new EntityNotFoundException("Empty key for attribute or value");
        }

        if (attributeValueKey.getAttributeKey().getId() != null &&
                attributeValueKey.getValueKey().getId() != null) {
            return attributeValueRepository.findByAttribute_AttributeNameAndValue_Value(
                            attributeValueKey.getAttributeKey().getAttributeName(),
                            attributeValueKey.getValueKey().getValue())
                    .orElseThrow(() -> new EntityNotFoundException("Attribute value not found with given attribute id:" + attributeValueKey.getAttributeKey().getId() + " , value id: " + attributeValueKey.getValueKey().getId()));
        }
        if (StringUtils.hasText(attributeValueKey.getAttributeKey().getAttributeName()) &&
                StringUtils.hasText(attributeValueKey.getValueKey().getValue())) {
            return attributeValueRepository.findByAttribute_AttributeNameAndValue_Value(
                            attributeValueKey.getAttributeKey().getAttributeName(),
                            attributeValueKey.getValueKey().getValue())
                    .orElseThrow(() -> new EntityNotFoundException("Attribute value not found with given attribute name:" + attributeValueKey.getAttributeKey().getAttributeName() + " , value: " + attributeValueKey.getValueKey().getValue()));
        }
        throw new EntityNotFoundException("Insufficient data on attribute key");
    }

    @Override
    public Map<AttributeKeyValue, AttributeValue> findOrCreateAttributeValue(Set<AttributeKeyValue> attributeValues) {
        if (attributeValues == null) {
            throw new IllegalArgumentException("Attributes must not be null!");
        }
        Set<String> attributeNames = attributeValues.stream().map(AttributeKeyValue::getAttributeName).collect(Collectors.toSet());
        Set<String> valueValues = attributeValues.stream().map(AttributeKeyValue::getValue).collect(Collectors.toSet());

        Map<String, Attribute> attributes = attributeService.findOrCreateAttributes(attributeNames);
        Map<String, Value> values = valueService.findOrCreateValues(valueValues);

        Set<AttributeKeyValue> normalizedAttributeKeyValues = attributeValues.stream()
                .map(entry -> AttributeKeyValue.builder()
                        .attributeName(StringConverter.formatName(entry.getAttributeName()))
                        .value(StringConverter.formatName(entry.getValue()))
                        .build())
                .collect(Collectors.toSet());
        List<AttributeValue> existingAttributeValues = normalizedAttributeKeyValues.stream()
                .map(entry -> attributeValueRepository.findByAttribute_AttributeNameAndValue_Value(entry.getAttributeName(), entry.getValue()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        Set<AttributeKeyValue> existingAttributeKeyValues = existingAttributeValues.stream()
                .map(entry -> AttributeKeyValue.builder()
                        .attributeName(StringConverter.formatName(entry.getAttribute().getAttributeName()))
                        .value(StringConverter.formatName(entry.getValue().getValue()))
                        .build())
                .collect(Collectors.toSet());

        Set<AttributeKeyValue> missingAttributeKeyValues = normalizedAttributeKeyValues.stream()
                .filter(attributeKeyValue -> !existingAttributeKeyValues.contains(attributeKeyValue))
                .collect(Collectors.toSet());


        List<AttributeValue> newAttributeValues = missingAttributeKeyValues.stream()
                .map(attributeKeyValue -> {
                    AttributeValue attributeValue = new AttributeValue();
                    Attribute attribute = attributes.get(attributeKeyValue.getAttributeName());
                    Value value = values.get(attributeKeyValue.getValue());

                    if (attribute == null || value == null) {
                        throw new IllegalArgumentException("Attribute or Value not found for: " + attributeKeyValue);
                    }

                    attributeValue.setAttribute(attribute);
                    attributeValue.setValue(value);

                    return attributeValue;
                })
                .collect(Collectors.toList());

        attributeValueRepository.saveAll(newAttributeValues);

        existingAttributeValues.addAll(newAttributeValues);

        return existingAttributeValues.stream()
                .collect(Collectors
                        .toMap(attributeValue -> AttributeKeyValue.builder()
                                        .attributeName(attributeValue.getAttribute().getAttributeName())
                                        .value(attributeValue.getValue().getValue())
                                        .build()
                                , attributeValue -> attributeValue)
                );

    }

/*    private Optional<AttributeValue> findByAttributeNameAndValue(AttributeValueKey attributeValueKey) {
        if (StringUtils.hasText(attributeValueKey.getAttributeKey().getAttributeName()) &&
                StringUtils.hasText(attributeValueKey.getValueKey().getValue())) {
            return attributeValueRepository.findByAttribute_AttributeNameAndValue_Value(
                    attributeValueKey.getAttributeKey().getAttributeName(),
                    attributeValueKey.getValueKey().getValue()
            );
        }
        return Optional.empty();
    }*/

 /*   private Optional<AttributeValue> findByAttributeIdAndValueId(AttributeValueKey attributeValueKey) {
        if (attributeValueKey.getAttributeKey().getId() != null &&
                attributeValueKey.getValueKey().getId() != null) {
            return attributeValueRepository.findByAttribute_IdAndValue_Id(
                    attributeValueKey.getAttributeKey().getId(),
                    attributeValueKey.getValueKey().getId()
            );
        }
        return Optional.empty();
    }*/
}

