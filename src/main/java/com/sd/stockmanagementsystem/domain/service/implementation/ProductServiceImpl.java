package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetProductByIdResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
    public void deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.delete(product.get());
        }
        else
            throw new EntityNotFoundException("Product id does not exist!");
    }


    @Override
    public Product findProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        else{
            throw new EntityNotFoundException("Product id does not exist!");
        }
    }

    @Override
    public void updateProductQuantity(String name, double quantity, TransactionEnumeration.TransactionType transactionType) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isPresent()){
            Product productObj = product.get();
            productObj.setUpdatedAt(Instant.now());
            if (transactionType == TransactionEnumeration.TransactionType.BUY){
                productObj.setQuantity(productObj.getQuantity() + quantity);
            }
            else{
                productObj.setQuantity(productObj.getQuantity() - quantity);
            }
            productRepository.save(productObj);
        }
        else{
            throw new EntityNotFoundException("Product id does not exist!");
        }
    }

    @Override
    public List<GetAllProductsResponseDTO> getAllProducts() {
        List<Product> allProductsList = productRepository.findAll();
        List<GetAllProductsResponseDTO> getAllProductsResponseDTOs = new ArrayList<>();
        for (Product product: allProductsList){
            getAllProductsResponseDTOs.add(productMapperService.forResponse().map(product, GetAllProductsResponseDTO.class));
        }
        return getAllProductsResponseDTOs;
    }

    @Override
    public GetProductByIdResponseDTO getProductById(long id) {
        Product product = findProductById(id);
        GetProductByIdResponseDTO getProductByIdResponseDTO = productMapperService.forResponse().map(product, GetProductByIdResponseDTO.class);
        return getProductByIdResponseDTO;
    }

    @Override
    public List<GetAllProductsBySubstringResponseDTO> getAllProductsBySubstring(String subString) {
        List<Product> allProductsList = productRepository.findBySubstring(subString);
        List<GetAllProductsBySubstringResponseDTO> getAllProductsBySubstringResponseDTOs = new ArrayList<>();
        for (Product product: allProductsList){
            getAllProductsBySubstringResponseDTOs
                    .add
                            (productMapperService
                                    .forResponse()
                                    .map(product, GetAllProductsBySubstringResponseDTO.class)
                            );
        }
        return getAllProductsBySubstringResponseDTOs;
    }

    @Override
    public Product findProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isPresent()){
            return product.get();
        }
        else {
            throw new EntityNotFoundException("Product name does not exist!");
        }
    }
}
