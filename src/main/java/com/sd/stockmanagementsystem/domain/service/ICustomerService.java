package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.*;

public interface ICustomerService extends AddCustomerUseCase,
        UpdateCustomerUseCase,
        DeleteCustomerUseCase,
        FindCustomerByCustomerKeyUseCase,
        GetCustomerByCustomerKeyUseCase,
        GetAllCustomersUseCase ,
        GetAllCustomersBySubstringUseCase{
}
