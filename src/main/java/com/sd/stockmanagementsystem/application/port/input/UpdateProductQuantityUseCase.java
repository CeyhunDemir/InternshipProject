package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.UpdateProductQuantityRequestDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;

public interface UpdateProductQuantityUseCase {
    void updateProductQuantity(UpdateProductQuantityRequestDTO updateProductQuantityRequestDTO);
}
