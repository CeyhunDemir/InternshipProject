package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.AddMultipleAttributesRequestDTO;
import com.sd.stockmanagementsystem.domain.model.ProductAttributeValue;
import com.sd.stockmanagementsystem.domain.service.IProductAttributeValueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attribute")
@RequiredArgsConstructor
@Validated
public class AttributeController {
    private final IProductAttributeValueService productAttributeValueService;

    @PostMapping
    public void AddMultipleAttributeForProduct(@Valid @RequestBody AddMultipleAttributesRequestDTO request) {
        productAttributeValueService.addMultipleAttributes(request);
    }
}
