package com.sd.stockmanagementsystem.application.port.input;

import com.sd.stockmanagementsystem.domain.model.Product;

import java.util.Map;
import java.util.Set;

public interface FindProductsByNamesUseCase {
    Map<String, Product> findProductsByNames(Set<String> names);
}
