package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.request.AddAttributeRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteAttributeRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateAttributeRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Attribute;
import com.sd.stockmanagementsystem.domain.service.IAttributeService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.AttributeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

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
    public Attribute findAttributeByAttributeKey(AttributeKey attributeKey) {
        if (attributeKey.getId() != null) {
            Optional<Attribute> attribute = attributeRepository.findById(attributeKey.getId());
            if (attribute.isPresent()) {
                return attribute.get();
            } else if (StringUtils.hasText(attributeKey.getAttributeName())) {
                Optional<Attribute> attribute2 = attributeRepository.findByAttributeName(attributeKey.getAttributeName());
                if (attribute2.isPresent()) {
                    return attribute2.get();
                }
                return this.addAttribute(new AddAttributeRequestDTO(attributeKey.getAttributeName()));
            } else throw new EntityNotFoundException("Attribute id not found with id: " + attributeKey.getId());
        } else if (StringUtils.hasText(attributeKey.getAttributeName())) {
            Optional<Attribute> attribute = attributeRepository.findByAttributeName(attributeKey.getAttributeName());
            if (attribute.isPresent()) {
                return attribute.get();
            }
            return this.addAttribute(new AddAttributeRequestDTO(attributeKey.getAttributeName()));
        } else {
            throw new EntityNotFoundException("Given Key is empty!");
        }
    }

    @Override
    public void updateAttribute(UpdateAttributeRequestDTO updateAttributeRequestDTO) {
        Attribute attribute = findAttributeByAttributeKey(updateAttributeRequestDTO.getAttributeKey());
        attribute.setAttributeName(updateAttributeRequestDTO.getAttributeName());
        attributeRepository.save(attribute);
    }


}
