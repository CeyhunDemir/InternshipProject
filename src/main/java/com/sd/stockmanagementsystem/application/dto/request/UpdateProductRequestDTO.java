package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidEnum;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfUnitType;
import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*@ValidQuantityOfUnitType(message = "Quantity must be an integer when the unit type is COUNT")*/
public class UpdateProductRequestDTO {

    private long id;

    @NotBlank
    @Size(max = 150, message = "Product name can not exceed 150 characters.")
    private String name;

    @NotNull(message = "Product unit type can not be null!")
    @ValidEnum(message = "unitType should be one of the unit types!", enumClass = ProductEnumeration.UnitType.class)
    private ProductEnumeration.UnitType unitType;

    @NotNull(message = "Product price can not be null!")
    @Positive
    private double price;

    /*    private Map<String,String> attributes;*/
}
