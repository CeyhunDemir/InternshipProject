package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;

public interface DeleteCustomerUseCase {
    void deleteCustomer(CustomerKey customerKey);
}
