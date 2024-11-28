package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.AddProductUseCase;
import com.sd.stockmanagementsystem.application.port.input.DeleteProductUseCase;
import com.sd.stockmanagementsystem.application.port.input.GetProductByIdUseCase;
import com.sd.stockmanagementsystem.application.port.input.UpdateProductUseCase;

public interface IProductService extends AddProductUseCase, UpdateProductUseCase, DeleteProductUseCase, GetProductByIdUseCase {

}
