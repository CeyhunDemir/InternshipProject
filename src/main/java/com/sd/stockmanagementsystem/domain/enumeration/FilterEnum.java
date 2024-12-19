package com.sd.stockmanagementsystem.domain.enumeration;

import lombok.Getter;

public class FilterEnum {
    public enum Order{
        ASCENDING, DESCENDING
    }
    @Getter
    public enum OrderType {
        DATE("transactionDate"),
        QUANTITY("quantity"),
        PRICE("totalPrice");

        private final String propertyName;

        OrderType(String propertyName) {
            this.propertyName = propertyName;
        }

    }
}
