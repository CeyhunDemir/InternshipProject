package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidEnum;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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
public class UpdateProductQuantityRequestDTO {
    private long id;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private double quantity;
    @ValidEnum(message = "Transaction type must be either BUY or SELL", enumClass = TransactionEnumeration.TransactionType.class)
    private TransactionEnumeration.TransactionType transactionType;
}
