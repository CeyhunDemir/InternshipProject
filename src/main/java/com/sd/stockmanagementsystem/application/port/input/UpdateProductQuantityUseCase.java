package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;

public interface UpdateProductQuantityUseCase {
    void updateProductQuantity(String name, double quantity, TransactionEnumeration.TransactionType transactionType);
}
