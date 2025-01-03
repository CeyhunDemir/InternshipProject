package com.sd.stockmanagementsystem.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMultipleAttributesRequestDTO {
    private String productName;
    private Map<String, String> attributes;
}