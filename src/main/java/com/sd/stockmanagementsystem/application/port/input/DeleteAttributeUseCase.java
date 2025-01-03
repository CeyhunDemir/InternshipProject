package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.request.DeleteAttributeRequestDTO;

public interface DeleteAttributeUseCase {
    void deleteAttribute(DeleteAttributeRequestDTO deleteAttributeRequestDTO);
}
