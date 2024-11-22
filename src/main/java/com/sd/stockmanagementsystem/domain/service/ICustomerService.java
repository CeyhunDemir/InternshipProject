package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddCustomerUseCase;
import com.sd.stockmanagementsystem.application.port.input.DeleteCustomerUseCase;
import com.sd.stockmanagementsystem.application.port.input.UpdateCustomerUseCase;

public interface ICustomerService extends AddCustomerUseCase, UpdateCustomerUseCase, DeleteCustomerUseCase {
}
