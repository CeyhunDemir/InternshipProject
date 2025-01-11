package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidNumberOfFields;
import com.sd.stockmanagementsystem.domain.model.Location;
import com.sd.stockmanagementsystem.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidNumberOfFields(fields = {"productName", "barcode"})
public class AddStockRequestDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String locationName;

    private String productName;

    private String barcode;

    private Product product;

    private Location location;
}
