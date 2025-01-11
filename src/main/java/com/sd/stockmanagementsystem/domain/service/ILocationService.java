package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddLocationUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindLocationByLocationKeyUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindLocationsByIdsUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindLocationsByNamesUseCase;

public interface ILocationService extends
        AddLocationUseCase,
        FindLocationByLocationKeyUseCase,
        FindLocationsByNamesUseCase,
        FindLocationsByIdsUseCase {
}
