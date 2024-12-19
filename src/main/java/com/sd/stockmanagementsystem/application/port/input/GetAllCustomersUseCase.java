package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersResponseDTO;

import java.util.List;

public interface GetAllCustomersUseCase {
    List<GetAllCustomersResponseDTO> getAllCustomers();
}
