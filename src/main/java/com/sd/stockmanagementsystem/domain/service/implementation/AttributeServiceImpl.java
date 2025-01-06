package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.request.AddAttributeRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteAttributeRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateAttributeRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import com.sd.stockmanagementsystem.domain.service.IAttributeService;
import com.sd.stockmanagementsystem.domain.util.StringConverter;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.AttributeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttributeServiceImpl implements IAttributeService {
    private final AttributeRepository attributeRepository;
    private final IGeneralMapperService attributeMapperService;


    //Attribute Table Functions
    @Override
    public Attribute addAttribute(AddAttributeRequestDTO addAttributeRequestDTO) {
        addAttributeRequestDTO.setAttributeName(addAttributeRequestDTO.getAttributeName().toLowerCase());
        Attribute attribute = attributeMapperService.forRequest().map(addAttributeRequestDTO, Attribute.class);
        attributeRepository.save(attribute);
        return attribute;
    }

    @Override
    public void deleteAttribute(DeleteAttributeRequestDTO deleteAttributeRequestDTO) {
        AttributeKey attributeKey = attributeMapperService.forRequest().map(deleteAttributeRequestDTO, AttributeKey.class);
        Attribute attribute = findAttributeByAttributeKey(attributeKey);
        attributeRepository.delete(attribute);
    }

    @Override
    public Attribute findAttributeByAttributeKey(AttributeKey attributeKey) throws EntityNotFoundException {
        if (attributeKey == null) {
            throw new EntityNotFoundException("Given Key is empty!");
        }
        if (attributeKey.getId() != null) {
            return attributeRepository.findById(attributeKey.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Attribute not found with id: " + attributeKey.getId()));
        }
        if (StringUtils.hasText(attributeKey.getAttributeName())) {
            return attributeRepository.findByAttributeName(attributeKey.getAttributeName())
                    .orElseThrow(() -> new EntityNotFoundException("Attribute not found with name:" + attributeKey.getAttributeName()));

        }
        throw new EntityNotFoundException("Attribute not found!");
    }

    @Override
    public void updateAttribute(UpdateAttributeRequestDTO updateAttributeRequestDTO) {
        Attribute attribute = findAttributeByAttributeKey(updateAttributeRequestDTO.getAttributeKey());
        attribute.setAttributeName(updateAttributeRequestDTO.getAttributeName());
        attributeRepository.save(attribute);
    }


    @Override
    public Map<String, Attribute> findOrCreateAttributes(Set<String> attributeNames) {
        if (attributeNames == null || attributeNames.isEmpty()) {
            throw new IllegalArgumentException("Attribute names must not be null or empty.");
        }

        // Normalize attribute names (e.g., to lowercase)
        Set<String> normalizedNames = attributeNames.stream()
                .map(StringConverter::formatName)
                .collect(Collectors.toSet());

        // Step 1: Fetch existing attributes in bulk
        List<Attribute> existingAttributes = attributeRepository.findByAttributeNameIn(normalizedNames);

        // Step 2: Determine missing attributes
        Set<String> existingNames = existingAttributes.stream()
                .map(Attribute::getAttributeName)
                .collect(Collectors.toSet());

        Set<String> missingNames = normalizedNames.stream()
                .filter(name -> !existingNames.contains(name))
                .collect(Collectors.toSet());

        // Step 3: Create missing attributes
        List<Attribute> newAttributes = missingNames.stream()
                .map(name -> {
                    Attribute attribute = new Attribute();
                    attribute.setAttributeName(name);
                    return attribute;
                })
                .collect(Collectors.toList());

        attributeRepository.saveAll(newAttributes);

        // Combine existing and newly created attributes
        existingAttributes.addAll(newAttributes);

        // Return the attributes as a map for quick lookup
        return existingAttributes.stream()
                .collect(Collectors.toMap(Attribute::getAttributeName, attribute -> attribute));
    }



}
