package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.MoveMultipleProductsInStockRequestDTO;
import com.sd.stockmanagementsystem.domain.service.IStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
@Validated
public class StockController {
    private final IStockService stockService;

    @PostMapping("/move/multiple")
    public ResponseEntity<Void> moveMultipleProductsInStock(@RequestBody @Valid MoveMultipleProductsInStockRequestDTO request) {
        stockService.moveMultipleProductsInStock(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
