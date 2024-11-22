package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfUnitType;
import com.sd.stockmanagementsystem.domain.enumeration.ModelEnumeration.unitType;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidQuantityOfUnitType(message = "Quantity must be an integer when the unit type is COUNT")
public class AddProductRequestDTO {
    @NotNull(message = "Product name can not be null!")
    @NotBlank(message = "Product name can not be blank!")
    @Size(max = 100, message = "Product name can not exceed 100 characters.")
    private String name;

    @NotNull(message = "Product unit type can not be null!")
    private unitType unitType;

    @NotNull(message = "Product quantity can not be null!")
    @Positive
    private double quantity;

    @NotNull(message = "Product price can not be null!")
    @Positive
    private double price;
}
