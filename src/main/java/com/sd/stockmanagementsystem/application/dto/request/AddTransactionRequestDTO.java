package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.application.dto.valid.ValidEnum;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfProduct;
import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfTransaction;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidQuantityOfTransaction(message = "This much amount of product does not exist in stocks!!!")
@ValidQuantityOfProduct(message = "Cant buy/sell COUNT unit type products with decimal values.")
public class AddTransactionRequestDTO {
/*    @NotNull
    private Product product;

    @NotNull
    private Customer customer;*/

    @NotNull
    private long product_id;

/*    @NotNull*/
    private Long customer_id;

    private Instant transactionDate;

    @NotNull
    @Positive
    private double quantity;

    @NotNull
    private TransactionEnumeration.TransactionType transactionType;

    @NotNull
    @PositiveOrZero
    private double totalPrice;
}
