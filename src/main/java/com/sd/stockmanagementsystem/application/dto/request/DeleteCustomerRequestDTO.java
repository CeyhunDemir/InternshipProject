package com.sd.stockmanagementsystem.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCustomerRequestDTO {
    @NotNull(message = "Customer id can not be null!")
    @Positive
    private long id;
}
