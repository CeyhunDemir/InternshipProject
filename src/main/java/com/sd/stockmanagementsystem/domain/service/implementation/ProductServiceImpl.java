package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final ProductRepository productRepository;
    private final IGeneralMapperService productMapperService;
    @Override
    @Transactional
    public void addProduct(AddProductRequestDTO addProductRequestDto) {
        Product product = productMapperService.forRequest().map(addProductRequestDto, Product.class);
        productRepository.save(product);

    }

    @Override
    @Transactional
    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(updateProductRequestDTO.getId());
        if (product.isPresent()) {
            Product updateProduct = productMapperService.forRequest().map(updateProductRequestDTO, Product.class);
            updateProduct.setCreatedAt(product.get().getCreatedAt());
            productRepository.save(updateProduct);
        }
        else
            throw new EntityNotFoundException("Product id does not exist!");
    }

    @Override
    public void deleteProduct(DeleteProductRequestDTO deleteProductRequestDTO) {
        Optional<Product> product = productRepository.findById(deleteProductRequestDTO.getId());
        if (product.isPresent()){
            productRepository.delete(product.get());
        }
        else
            throw new EntityNotFoundException("Product id does not exist!");
    }


    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        else{
            throw new EntityNotFoundException("Product id does not exist!");
        }
    }
}
