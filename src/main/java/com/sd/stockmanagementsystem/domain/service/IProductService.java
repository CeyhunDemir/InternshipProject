package com.sd.stockmanagementsystem.domain.service;

import com.sd.stockmanagementsystem.application.port.input.*;

public interface IProductService extends AddProductUseCase, UpdateProductUseCase, DeleteProductUseCase, GetProductByIdUseCase, UpdateProductQuantityUseCase, GetAllProductsUseCase {

}
