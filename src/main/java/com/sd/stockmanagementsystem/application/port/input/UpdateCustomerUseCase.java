package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;

public interface UpdateCustomerUseCase {
    void updateCustomer(UpdateCustomerRequestDTO updateCustomerRequestDTO);
}
