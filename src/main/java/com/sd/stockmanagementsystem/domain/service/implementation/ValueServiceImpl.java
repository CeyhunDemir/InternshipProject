package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
import com.sd.stockmanagementsystem.application.dto.request.AddValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Value;
import com.sd.stockmanagementsystem.domain.service.IValueService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ValueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValueServiceImpl implements IValueService {
    private final ValueRepository valueRepository;

    @Override
    public Value addValue(AddValueRequestDTO addValueRequestDTO) {
        Value value = Value.builder().value(addValueRequestDTO.getValue()).build();
        valueRepository.save(value);
        return value;
    }

    @Override
    public Value findValueWithValueKey(ValueKey valueKey) {
        if (valueKey.getId() != null) {
            Optional<Value> value = valueRepository.findById(valueKey.getId());
            if (value.isPresent()) {
                return value.get();
            } else if (StringUtils.hasText(valueKey.getValue())) {
                Optional<Value> value2 = valueRepository.findByValue(valueKey.getValue());
                if (value2.isPresent()) {
                    return value2.get();
                } else {
                    return addValue(new AddValueRequestDTO(valueKey.getValue()));
                }
            } else throw new EntityNotFoundException("No value exists with given id: " + valueKey.getId());
        } else if (StringUtils.hasText(valueKey.getValue())) {
            Optional<Value> value = valueRepository.findByValue(valueKey.getValue());
            if (value.isPresent()) {
                return value.get();
            } else {
                return addValue(new AddValueRequestDTO(valueKey.getValue()));
            }
        } else throw new EntityNotFoundException("Given valueKey is empty");

    }
}
