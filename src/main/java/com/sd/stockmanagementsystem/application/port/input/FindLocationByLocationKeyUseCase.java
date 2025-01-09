package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.application.dto.core.LocationKey;
import com.sd.stockmanagementsystem.domain.model.Location;

public interface FindLocationByLocationKeyUseCase {
    Location findByLocationKey(LocationKey locationKey);
}
