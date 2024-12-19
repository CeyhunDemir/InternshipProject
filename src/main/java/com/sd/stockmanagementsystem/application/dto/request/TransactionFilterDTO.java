package com.sd.stockmanagementsystem.application.dto.request;

import com.sd.stockmanagementsystem.domain.enumeration.FilterEnum;
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
public class TransactionFilterDTO {
    private String productName;
    private Double minQuantity;
    private Double maxQuantity;
    private Double minPrice;
    private Double maxPrice;
    private String afterDate;
    private String beforeDate;
    private FilterEnum.Order order;
    private FilterEnum.OrderType orderType;
    private TransactionEnumeration.TransactionType transactionType;

}
