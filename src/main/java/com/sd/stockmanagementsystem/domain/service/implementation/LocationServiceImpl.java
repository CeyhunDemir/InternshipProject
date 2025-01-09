package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.LocationKey;
import com.sd.stockmanagementsystem.domain.model.Location;
import com.sd.stockmanagementsystem.domain.service.ILocationService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements ILocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location findByLocationKey(LocationKey locationKey) {
        if (locationKey == null) {
            throw new EntityNotFoundException("Given Key is empty!");
        }
        if (locationKey.getId() != null) {
            Optional<Location> location = locationRepository.findById(locationKey.getId());
            if (location.isPresent()) {
                return location.get();
            }
        }
        if (StringUtils.hasText(locationKey.getName())) {
            Optional<Location> location = locationRepository.findByName(locationKey.getName());
            if (location.isPresent()) {
                return location.get();
            }
        }
        if (locationKey.getId() != null) {
            throw new EntityNotFoundException("Location id not found with id: " + locationKey.getId());
        }
        if (StringUtils.hasText(locationKey.getName())) {
            throw new EntityNotFoundException("Location name not found with name: " + locationKey.getName());
        }
        throw new EntityNotFoundException("Location is not found!");
    }

    @Override
    public Map<String, Location> findLocationsByNames(Set<String> names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyMap();
        }
        List<Location> locations = locationRepository.findByNameIn(names);
        return locations.stream()
                .collect(Collectors.toMap(Location::getName, location -> location));
    }
}
