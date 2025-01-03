package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfProduct;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfTransaction;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "addTransactionByBarcodeRequestDTOBuilder")
@AllArgsConstructor
@NoArgsConstructor
@ValidQuantityOfTransaction(message = "This much amount of product does not exist in stocks!!!")
@ValidQuantityOfProduct(message = "Cant buy/sell COUNT unit type products with decimal values.")
public class AddTransactionByBarcodeRequestDTO extends AddTransactionRequestDTO {
    private String barcode;

    @NotNull
    @Positive
    private double quantity;

    @NotNull
    private TransactionEnumeration.TransactionType transactionType;
}
