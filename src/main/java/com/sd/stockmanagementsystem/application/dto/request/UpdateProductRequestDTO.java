package com.sd.stockmanagementsystem.application.dto.request;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidQuantityOfUnitType(message = "Quantity must be an integer when the unit type is COUNT")
public class UpdateProductRequestDTO {
    @NotNull(message = "Product id can not be null!")
    private long id;
    @NotNull(message = "Product name can not be null!")
    @NotBlank(message = "Product name can not be blank!")
    @Size(max = 100, message = "Product name can not exceed 100 characters.")
    private String name;

    @NotNull(message = "Product unit type can not be null!")
    private ProductEnumeration.UnitType unitType;

    @NotNull(message = "Product quantity can not be null!")
    @Positive
    private double quantity;

    @NotNull(message = "Product price can not be null!")
    @Positive
    private double price;
}
