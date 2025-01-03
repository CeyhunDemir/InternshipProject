package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.core.AttributeKey;
import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.core.ValueKey;
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
public class AddProductAttributeValueRequestDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String productName;
    @NotNull
    @NotBlank
    @NotEmpty
    private String attributeName;
    @NotNull
    @NotBlank
    @NotEmpty
    private String value;
}
