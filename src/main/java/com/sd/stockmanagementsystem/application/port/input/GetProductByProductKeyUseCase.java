package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.response.GetProductByProductKeyResponseDTO;

public interface GetProductByProductKeyUseCase {
    GetProductByProductKeyResponseDTO getProductByProductKey(ProductKey productKey);
}
