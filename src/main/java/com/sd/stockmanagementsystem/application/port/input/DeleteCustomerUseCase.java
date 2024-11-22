package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.DeleteCustomerRequestDTO;

public interface DeleteCustomerUseCase {
    void deleteCustomer(DeleteCustomerRequestDTO deleteCustomerRequestDTO);
}
