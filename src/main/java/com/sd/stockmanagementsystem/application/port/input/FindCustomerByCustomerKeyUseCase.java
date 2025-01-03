package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.domain.model.Customer;

public interface FindCustomerByCustomerKeyUseCase {
    Customer findCustomerByCustomerKey(CustomerKey customerKey);
}
