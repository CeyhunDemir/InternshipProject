package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidEnum;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfProduct;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfTransaction;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidQuantityOfTransaction(message = "This much amount of product does not exist in stocks!!!")
@ValidQuantityOfProduct(message = "Cant buy/sell COUNT unit type products with decimal values.")
public class AddTransactionRequestDTO {
    @NotNull
    private Product product;

    @NotNull
    private Customer customer;

    @NotNull
    private double quantity;

    @NotNull
    @ValidEnum(enumClass = TransactionEnumeration.TransactionType.class, message = "No such transaction type exists. Only BUY or SELL")
    private TransactionEnumeration.TransactionType transactionType;

    @NotNull
    private double totalPrice;
}
