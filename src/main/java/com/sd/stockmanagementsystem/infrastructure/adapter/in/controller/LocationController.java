package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.AddLocationRequestDTO;
import com.sd.stockmanagementsystem.domain.service.ILocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
@Validated
public class LocationController {
    private final ILocationService locationService;

    @PostMapping
    public ResponseEntity<Void> addLocation(@RequestBody AddLocationRequestDTO locationRequestDTO) {
        locationService.addLocation(locationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
