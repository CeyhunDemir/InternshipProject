package com.sd.stockmanagementsystem.application.dto.response;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionByIdResponseDTO {
    private String productName;
    private String customerName;
    private Instant transactionDate;
    private double quantity;
    private TransactionEnumeration.TransactionType transactionType;
    private double totalPrice;
}
