package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.core.AttributeValueKey;
import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
import com.sd.stockmanagementsystem.application.dto.request.AddAttributeValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import com.sd.stockmanagementsystem.domain.model.Value;
import com.sd.stockmanagementsystem.domain.service.IAttributeService;
import com.sd.stockmanagementsystem.domain.service.IAttributeValueService;
import com.sd.stockmanagementsystem.domain.service.IValueService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.AttributeRepository;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.AttributeValueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttributeValueServiceImpl implements IAttributeValueService {
    private final IAttributeService attributeService;
    private final IValueService valueService;
    private final IGeneralMapperService attributeValueMapperService;
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeRepository attributeRepository;

    @Override
    public AttributeValue addAttributeValue(AddAttributeValueRequestDTO addAttributeValueRequestDTO) {
        AttributeKey attributeKey = attributeValueMapperService.forRequest().map(addAttributeValueRequestDTO, AttributeKey.class);
        ValueKey valueKey = attributeValueMapperService.forRequest().map(addAttributeValueRequestDTO, ValueKey.class);
        Value value = valueService.findValueWithValueKey(valueKey);
        Attribute attribute = attributeService.findAttributeByAttributeKey(attributeKey);
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttribute(attribute);
        attributeValue.setValue(value);
        attributeValueRepository.save(attributeValue);
        return attributeValue;
    }


    @Override
    public AttributeValue findAttributeValue(AttributeValueKey attributeValueKey) {
        if (attributeValueKey.getValueKey() == null || attributeValueKey.getAttributeKey() == null) {
            throw new EntityNotFoundException("Empty key for attribute or value");
        } else if (StringUtils.hasText(attributeValueKey.getAttributeKey().getAttributeName()) && StringUtils.hasText(attributeValueKey.getValueKey().getValue())) {
            Optional<AttributeValue> attributeValue = attributeValueRepository.findByAttribute_AttributeNameAndValue_Value(
                    attributeValueKey.getAttributeKey().getAttributeName(),
                    attributeValueKey.getValueKey().getValue());
            if (attributeValue.isPresent()) {
                return attributeValue.get();
            }
        } else if (attributeValueKey.getAttributeKey().getId() != null && attributeValueKey.getValueKey().getId() != null) {
            Optional<AttributeValue> attributeValue = attributeValueRepository.findByAttribute_IdAndValue_Id(
                    attributeValueKey.getAttributeKey().getId(),
                    attributeValueKey.getValueKey().getId());
            if (attributeValue.isPresent()) {
                return attributeValue.get();
            }

        }
        AddAttributeValueRequestDTO addAttributeValueRequestDTO = new AddAttributeValueRequestDTO();
        addAttributeValueRequestDTO.setAttributeKey(attributeValueKey.getAttributeKey());
        addAttributeValueRequestDTO.setValueKey(attributeValueKey.getValueKey());
        return this.addAttributeValue(addAttributeValueRequestDTO);
    }
}
