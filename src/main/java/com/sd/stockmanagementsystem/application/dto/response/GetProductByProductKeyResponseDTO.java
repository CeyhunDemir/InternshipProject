package com.sd.stockmanagementsystem.application.dto.response;

import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductByProductKeyResponseDTO {

    private String name;

    private ProductEnumeration.UnitType unitType;

    private double quantity;

    private double price;
}
