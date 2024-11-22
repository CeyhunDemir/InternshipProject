package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
public interface AddCustomerUseCase {
    void addCustomer(AddCustomerRequestDTO addCustomerRequestDTO);
}
