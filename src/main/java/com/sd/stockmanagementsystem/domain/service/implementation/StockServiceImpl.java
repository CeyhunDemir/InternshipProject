package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.LocationKey;
import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.core.StockKey;
import com.sd.stockmanagementsystem.domain.model.Stock;
import com.sd.stockmanagementsystem.domain.service.IStockService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {
    private final StockRepository stockRepository;

    @Override
    public Double findQuantityInStockByStockKey(StockKey stockKey) {
        Optional<Stock> stock = stockRepository.findByProduct_NameOrProduct_BarcodeAndLocation_Name(stockKey.getProductKey().getName(), stockKey.getProductKey().getBarcode(), stockKey.getLocationKey().getName());
        if (stock.isPresent()) {
            return stock.get().getQuantity();
        } else {
            throw new EntityNotFoundException("No Stock found for StockKey: " + stockKey);
        }
    }

    @Override
    public Stock findStockByStockKey(StockKey stockKey) {
        Optional<Stock> stock = stockRepository.findByProduct_NameOrProduct_BarcodeAndLocation_Name(stockKey.getProductKey().getName(), stockKey.getProductKey().getBarcode(), stockKey.getLocationKey().getName());
        if (stock.isPresent()) {
            return stock.get();
        } else {
            throw new EntityNotFoundException("No Stock found for StockKey: " + stockKey);
        }
    }

    @Override
    public Map<StockKey, Stock> findStocksByStockKeys(Set<StockKey> stockKeys) {
        if (stockKeys == null || stockKeys.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> productNames = new HashSet<>();
        Set<String> barcodes = new HashSet<>();
        Set<String> locationNames = new HashSet<>();

// Process all sets in one pass
        stockKeys.forEach(stockKey -> {
            if (stockKey.getProductKey().getName() != null) {
                productNames.add(stockKey.getProductKey().getName());
            }
            if (stockKey.getProductKey().getBarcode() != null) {
                barcodes.add(stockKey.getProductKey().getBarcode());
            }
            if (stockKey.getLocationKey().getName() != null) {
                locationNames.add(stockKey.getLocationKey().getName());
            }
        });

        List<Stock> stocks = stockRepository.findStocksByProductNamesOrBarcodesAndLocations(productNames, barcodes, locationNames);
        return stocks.stream().collect(Collectors.toMap(entry ->
                        StockKey.builder()
                                .locationKey(LocationKey.builder()
                                        .name(entry.getLocation().getName())
                                        .build())
                                .productKey(ProductKey.builder()
                                        .barcode(entry.getProduct().getBarcode())
                                        .name(entry.getProduct().getName())
                                        .build())
                                .build()
                ,
                entry -> entry
        ));
    }
}
