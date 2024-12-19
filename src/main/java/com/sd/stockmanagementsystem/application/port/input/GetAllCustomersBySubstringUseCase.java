package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;

import java.util.List;

public interface GetAllCustomersBySubstringUseCase {
    List<GetAllCustomersBySubstringResponseDTO> getAllCustomersBySubstring(String substring);
}
