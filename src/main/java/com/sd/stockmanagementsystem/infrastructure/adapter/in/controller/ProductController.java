package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsResponseDTO;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final IProductService productService;
    @PostMapping
    public void addProduct(@RequestBody @Valid AddProductRequestDTO addProductRequestDto) {
        productService.addProduct(addProductRequestDto);
    }
    @PutMapping
    public void updateProduct(@RequestBody @Valid UpdateProductRequestDTO updateProductRequestDto) {
        productService.updateProduct(updateProductRequestDto);
    }
    @DeleteMapping
    public void deleteProduct(@RequestBody @Valid DeleteProductRequestDTO deleteProductRequestDto) {
        productService.deleteProduct(deleteProductRequestDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<GetAllProductsResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
