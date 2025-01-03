package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.domain.service.IStockService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {
    private final StockRepository stockRepository;
}
