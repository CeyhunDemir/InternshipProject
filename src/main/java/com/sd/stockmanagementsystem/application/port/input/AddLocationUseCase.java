package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.AddLocationRequestDTO;

public interface AddLocationUseCase {
    void addLocation(AddLocationRequestDTO addLocationRequestDTO);
}
