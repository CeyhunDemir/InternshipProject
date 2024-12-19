package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsBySubstringResponseDTO;

import java.util.List;

public interface GetAllProductsBySubstringUseCase {
    List<GetAllProductsBySubstringResponseDTO> getAllProductsBySubstring(String subString);
}
