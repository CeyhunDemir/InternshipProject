package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKeyValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMultipleAttributesRequestDTO {
    private Long productId;
    private Set<AttributeKeyValue> attributes;
}
