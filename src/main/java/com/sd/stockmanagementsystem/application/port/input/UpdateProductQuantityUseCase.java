package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;

public interface UpdateProductQuantityUseCase {
    void updateProductQuantity(long id, double quantity, TransactionEnumeration.TransactionType transactionType);
}
