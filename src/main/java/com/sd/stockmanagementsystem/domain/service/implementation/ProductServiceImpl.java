package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.request.AddProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductQuantityRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateProductRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.AddProductResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllProductsResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetProductByProductKeyResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.util.StringConverter;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.ProductMapper;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final ProductRepository productRepository;
    private final IGeneralMapperService productMapperService;
    private final ProductMapper productMapper;
    private final EntityManager entityManager;


    @Override
    public AddProductResponseDTO addProduct(AddProductRequestDTO addProductRequestDto) {
        Product product = productMapperService.forRequest().map(addProductRequestDto, Product.class);
        product.setEnabled(true);
        product.setOriginalName(addProductRequestDto.getName());
        product.setName(StringConverter.formatName(product.getName()));
        product.setQuantity(0);
        productRepository.save(product);
        productRepository.flush();
        return AddProductResponseDTO.builder().id(product.getId()).build();
    }

    @Override
    @Transactional
    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws EntityNotFoundException {
        Product product = findProductByProductKey(ProductKey.builder().id(updateProductRequestDTO.getId()).name(updateProductRequestDTO.getName()).build());
        productMapper.updateProductFromDTO(updateProductRequestDTO, product);
        /*product.setUpdatedAt(Instant.now());*/
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
        if (productKey == null) {
            throw new EntityNotFoundException("Given Key is empty!");
        }
        if (productKey.getId() != null) {
            Optional<Product> product = productRepository.findById(productKey.getId());
            if (product.isPresent()) {
                return product.get();
            }
        }
        if (StringUtils.hasText(productKey.getBarcode())) {
            Optional<Product> product = productRepository.findByBarcode(productKey.getBarcode());
            if (product.isPresent()) {
                return product.get();
            }
        }
        if (StringUtils.hasText(productKey.getName())) {
            Optional<Product> product = productRepository.findByName(productKey.getName());
            if (product.isPresent()) {
                return product.get();
            }
        }
        if (productKey.getName() != null) {
            throw new EntityNotFoundException("No product exists with the given name: " + productKey.getName());
        }
        if (productKey.getBarcode() != null) {
            throw new EntityNotFoundException("No product exists with the given barcode: " + productKey.getBarcode());
        }
        if (productKey.getId() != null) {
            throw new EntityNotFoundException("No product exists with the given id: " + productKey.getId());
        }
        throw new EntityNotFoundException("Product not found.");
    }

    @Override
    public Map<String, Product> findProductsByBarcodes(Set<String> barcodes) {
        if (barcodes == null || barcodes.isEmpty()) {
            return Collections.emptyMap();
        }

        // Query the repository for all matching barcodes
        List<Product> products = productRepository.findAllByBarcodeIn(barcodes);

        // Map the results by barcode
        return products.stream()
                .collect(Collectors.toMap(Product::getBarcode, product -> product));
    }

    @Override
    public Map<String, Product> findProductsByNames(Set<String> names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyMap();
        }

        // Query the repository for all matching barcodes
        List<Product> products = productRepository.findAllByNameIn(names);

        // Map the results by barcode
        return products.stream()
                .collect(Collectors.toMap(Product::getName, product -> product));
    }

    @Override
    public Product findProductByIdForUpdate(Long id) {
        Optional<Product> product = productRepository.findByIdForUpdate(id);
        if (product.isPresent()) {
            return product.get();
        } else throw new EntityNotFoundException("No product exists with the given id: " + id);
    }

    @Override
    @Transactional
    public void updateProductQuantityInBulk(Map<Long, Double> productQuantityMap) {
        List<Long> ids = new ArrayList<>(productQuantityMap.keySet());
        entityManager.createQuery("SELECT p FROM Product p WHERE p.id IN (:ids)")
                .setParameter("ids", ids)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // Lock rows for update
                .getResultList();


        StringBuilder query = new StringBuilder("UPDATE Product p SET p.quantity = CASE ");

        for (Map.Entry<Long, Double> entry : productQuantityMap.entrySet()) {
            query.append("WHEN p.id = ")
                    .append(entry.getKey())
                    .append(" THEN p.quantity + ")
                    .append(entry.getValue())
                    .append(" ");
        }

        query.append("END WHERE p.id IN (:ids)");

        entityManager.createQuery(query.toString())
                .setParameter("ids", ids)
                .executeUpdate();
    }

    @Override
    public Map<Long, Product> findProductsByIds(Set<Long> ids) {
        if (ids == null) {
            return Collections.emptyMap();
        }
        List<Product> products = productRepository.findAllByIdIn(ids);

        return products.stream().collect(Collectors.toMap(entry -> entry.getId(), entry -> entry));
    }
}
