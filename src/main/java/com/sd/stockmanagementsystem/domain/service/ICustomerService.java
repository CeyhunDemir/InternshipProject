package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.port.input.*;

import java.util.List;

public interface ICustomerService extends AddCustomerUseCase,
        UpdateCustomerUseCase,
        DeleteCustomerUseCase,
        FindCustomerByIdUseCase,
        FindCustomerbyNameUseCase,
        GetCustomerByIdUseCase,
        GetAllCustomersUseCase ,
        GetAllCustomersBySubstringUseCase{
}
