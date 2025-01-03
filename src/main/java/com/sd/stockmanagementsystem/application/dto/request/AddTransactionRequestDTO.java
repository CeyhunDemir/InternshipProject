package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionRequestDTO {

    protected String customer_name;

    @NotNull
    @PositiveOrZero
    protected double totalPrice;
}
