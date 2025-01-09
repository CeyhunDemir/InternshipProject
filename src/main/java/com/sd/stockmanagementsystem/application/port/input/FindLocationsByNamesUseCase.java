package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Location;

import java.util.Map;
import java.util.Set;

public interface FindLocationsByNamesUseCase {
    Map<String, Location> findLocationsByNames(Set<String> names);
}
