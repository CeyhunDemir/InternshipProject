package com.sd.stockmanagementsystem.domain.service.implementation;

import ch.qos.logback.core.util.StringUtil;
import com.sd.stockmanagementsystem.application.dto.core.*;
import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductQuantityRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetProductByProductKeyResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.model.ProductAttributeValue;
import com.sd.stockmanagementsystem.domain.service.IProductAttributeValueService;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.ProductMapper;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductAttributeValueRepository;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.sd.stockmanagementsystem.domain.model.Transaction_.transactionType;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final ProductRepository productRepository;
    private final IGeneralMapperService productMapperService;
    private final ProductMapper productMapper;


    @Override
    @Transactional
    public void addProduct(AddProductRequestDTO addProductRequestDto) {
        Product product = productMapperService.forRequest().map(addProductRequestDto, Product.class);
        product.setQuantity(0);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws EntityNotFoundException {
        Product product = productMapperService.forRequest().map(updateProductRequestDTO, Product.class);
        productMapper.updateProductFromDTO(updateProductRequestDTO, product);
        product.setUpdatedAt(Instant.now());
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(ProductKey productKey) {
        Product product = findProductByProductKey(productKey);
        productRepository.delete(product);
    }


    @Override
    @Transactional
    public void updateProductQuantity(UpdateProductQuantityRequestDTO updateProductQuantityRequestDTO) {
        ProductKey productKey = productMapperService.forRequest().map(updateProductQuantityRequestDTO, ProductKey.class);
        Product product = findProductByProductKey(productKey);
        product.setUpdatedAt(Instant.now());
        if (updateProductQuantityRequestDTO.getTransactionType().equals(TransactionEnumeration.TransactionType.BUY)) {
            product.setQuantity(product.getQuantity() + updateProductQuantityRequestDTO.getQuantity());
            productRepository.save(product);
        } else if (updateProductQuantityRequestDTO.getTransactionType().equals(TransactionEnumeration.TransactionType.SELL)) {
            product.setQuantity(product.getQuantity() - updateProductQuantityRequestDTO.getQuantity());
            productRepository.save(product);
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
    public GetProductByProductKeyResponseDTO getProductByProductKey(ProductKey productKey) {
        Product product = findProductByProductKey(productKey);
        GetProductByProductKeyResponseDTO getProductByProductKeyResponseDTO = productMapperService.forResponse().map(product, GetProductByProductKeyResponseDTO.class);
        return getProductByProductKeyResponseDTO;
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
    public Product findProductByProductKey(ProductKey productKey) {
        if (productKey.getId() != null) {
            Optional<Product> product = productRepository.findById(productKey.getId());
            if (product.isPresent()) {
                return product.get();
            } else if (StringUtils.hasText(productKey.getName())) {
                Optional<Product> product2 = productRepository.findByName(productKey.getName());
                if (product2.isPresent()) {
                    return product2.get();
                } else throw new EntityNotFoundException("Product name not found with name: " + productKey.getName());
            } else throw new EntityNotFoundException("Product id not found with id: " + productKey.getId());
        } else if (StringUtils.hasText(productKey.getName())) {
            Optional<Product> product = productRepository.findByName(productKey.getName());
            if (product.isPresent()) {
                return product.get();
            } else throw new EntityNotFoundException("Product name not found with name: " + productKey.getName());
        } else {
            throw new EntityNotFoundException("Given Key is empty!");
        }
    }
}
