package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Customer;

public interface FindCustomerByIdUseCase {
    Customer findCustomerById(long id);
}
