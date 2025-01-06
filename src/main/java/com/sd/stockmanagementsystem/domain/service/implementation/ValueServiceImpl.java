package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
import com.sd.stockmanagementsystem.application.dto.request.AddValueRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Value;
import com.sd.stockmanagementsystem.domain.service.IValueService;
import com.sd.stockmanagementsystem.domain.util.StringConverter;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ValueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Value findValueWithValueKey(ValueKey valueKey) throws EntityNotFoundException {
        if (valueKey == null) {
            throw new EntityNotFoundException("Given valueKey is empty");
        }
        if (valueKey.getId() != null) {
            return valueRepository.findById(valueKey.getId())
                    .or(() -> valueRepository.findByValue(valueKey.getValue()))
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Value not found with given id: " + valueKey.getId())
                    );
        }
        if (StringUtils.hasText(valueKey.getValue())) {
            return valueRepository.findByValue(valueKey.getValue())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Value not found with given value: " + valueKey.getValue())
                    );
        }
        throw new EntityNotFoundException("Value not found!");
    }


    @Override
    public Map<String, Value> findOrCreateValues(Set<String> valueValues) {
        if (valueValues == null || valueValues.isEmpty()) {
            throw new IllegalArgumentException("Attribute names must not be null or empty.");
        }

        // Format value values (e.g., to lowercase)
        Set<String> normalizedValueValues = valueValues.stream()
                .map(StringConverter::formatName)
                .collect(Collectors.toSet());

        // Step 1: Fetch existing values in bulk
        List<Value> existingValues = valueRepository.findByValueIn(normalizedValueValues);

        // Step 2: Determine missing values
        Set<String> existingValueValues = existingValues.stream()
                .map(Value::getValue)
                .collect(Collectors.toSet());

        Set<String> missingValueValues = normalizedValueValues.stream()
                .filter(valueValue -> !existingValueValues.contains(valueValue))
                .collect(Collectors.toSet());

        // Step 3: Create missing values
        List<Value> newValues = missingValueValues.stream()
                .map(valueValue -> {
                    Value value = new Value();
                    value.setValue(valueValue);
                    return value;
                })
                .collect(Collectors.toList());

        valueRepository.saveAll(newValues);

        // Combine existing and newly created attributes
        existingValues.addAll(newValues);

        // Return the attributes as a map for quick lookup
        return existingValues.stream()
                .collect(Collectors.toMap(Value::getValue, value -> value));
    }
}
