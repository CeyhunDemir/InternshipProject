package com.sd.stockmanagementsystem.application.dto.request;

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
public class AddProductRequestDTO {
    @NotNull(message = "Product name can not be null!")
    @NotBlank(message = "Product name can not be blank!")
    @Size(max = 150, message = "Product name can not exceed 150 characters.")
    private String name;

    @NotNull(message = "Product unit type can not be null!")
    private ProductEnumeration.UnitType unitType;

    @NotNull(message = "Product price can not be null!")
    @Positive
    private double price;

    private String barcode;


}
