package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByIdResponseDTO;

public interface GetCustomerByIdUseCase {
    GetCustomerByIdResponseDTO getCustomerById(long id);
}
