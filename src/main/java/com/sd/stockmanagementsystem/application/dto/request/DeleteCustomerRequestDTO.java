package com.sd.stockmanagementsystem.application.dto.request;

import jakarta.validation.constraints.NotNull;
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
    private long id;
}
