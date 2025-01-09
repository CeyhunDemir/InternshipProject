package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidNumberOfFields;
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
@ValidNumberOfFields(fields = {"barcode", "product_name"})
public class AddTransactionRequestDTO {

    private String customer_name;

    @NotNull
    @PositiveOrZero
    private double totalPrice;

    private String barcode;

    private String product_name;

    @NotNull
    private String locationName;

    @NotNull
    @Positive
    private double quantity;

    @NotNull
    private TransactionEnumeration.TransactionType transactionType;
}
