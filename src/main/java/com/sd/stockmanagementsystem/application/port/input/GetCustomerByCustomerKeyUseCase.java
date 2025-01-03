package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByCustomerKeyResponseDTO;

public interface GetCustomerByCustomerKeyUseCase {
    GetCustomerByCustomerKeyResponseDTO getCustomerByCustomerKey(CustomerKey customerKey);
}
