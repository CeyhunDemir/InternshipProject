package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.domain.model.AttributeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttributeRequestDTO {
    private AttributeKey attributeKey;
    private String attributeName;
}
