package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.application.dto.core.LocationKey;
import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.core.StockKey;
import com.sd.stockmanagementsystem.application.dto.request.AddMultipleTransactionsRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.AddTransactionRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.TransactionFilterDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllTransactionsResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.FilterEnum;
import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.*;
import com.sd.stockmanagementsystem.domain.service.*;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.TransactionRepository;
import com.sd.stockmanagementsystem.infrastructure.config.TransactionFilterSpecifications;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final IGeneralMapperService transactionMapperService;
    private final ICustomerService customerService;
    private final IStockService stockService;
    private final IProductService productService;
    private final ILocationService locationService;

    @Override
    @Transactional
    public void addTransaction(AddTransactionRequestDTO addTransactionRequestDTO) {
        /*Transaction transaction = Transaction.builder()
                .product(productService.findProductByProductKey(
                        ProductKey.builder().name(
                                        addTransactionByProductNameRequestDTO.getProduct_name())
                                .build()))
                .customer((addTransactionByProductNameRequestDTO.getCustomer_name() == null) ? null : customerService.findCustomerByCustomerKey(
                        CustomerKey.builder().name(
                                        addTransactionByProductNameRequestDTO.getCustomer_name())
                                .build()))
                .quantity(addTransactionByProductNameRequestDTO.getQuantity())
                .transactionType(addTransactionByProductNameRequestDTO.getTransactionType())
                .totalPrice(addTransactionByProductNameRequestDTO.getTotalPrice())
                .build();

        UpdateProductQuantityRequestDTO updateProductQuantityRequestDTO = UpdateProductQuantityRequestDTO.builder()
                .name(addTransactionByProductNameRequestDTO.getProduct_name())
                .quantity(addTransactionByProductNameRequestDTO.getQuantity())
                .transactionType(addTransactionByProductNameRequestDTO.getTransactionType())
                .build();

        productService.updateProductQuantity(updateProductQuantityRequestDTO);
        transactionRepository.save(transaction);*/
    }

    @Override
    public List<GetAllTransactionsResponseDTO> getAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<GetAllTransactionsResponseDTO> getAllTransactionsResponseDTOs = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            GetAllTransactionsResponseDTO transactionsResponseDTO = new GetAllTransactionsResponseDTO();
            transactionsResponseDTO.setId(transaction.getId());
            transactionsResponseDTO.setProductName(transaction.getStock().getProduct().getName());
            transactionsResponseDTO.setQuantity(transaction.getQuantity());
            transactionsResponseDTO.setTransactionType(transaction.getTransactionType());
            transactionsResponseDTO.setCustomerName("");
            if (transaction.getCustomer() != null){
                transactionsResponseDTO.setCustomerName(transaction.getCustomer().getName());
            }

            transactionsResponseDTO.setTotalPrice(transaction.getTotalPrice());
            transactionsResponseDTO.setTransactionDate(transaction.getTransactionDate());
            getAllTransactionsResponseDTOs.add(transactionsResponseDTO);
        }
        return getAllTransactionsResponseDTOs;

    }

    @Override
    public List<GetAllTransactionsResponseDTO> getAllTransactionsWithFilters(TransactionFilterDTO transactionFilterDTO) {
        Specification<Transaction> transactionSpecification = Specification.where(
                TransactionFilterSpecifications.hasName(transactionFilterDTO.getProductName()))
                .and(TransactionFilterSpecifications.hasType(transactionFilterDTO.getTransactionType()))
                .and(TransactionFilterSpecifications.betweenQuantity(transactionFilterDTO.getMinQuantity(), transactionFilterDTO.getMaxQuantity()))
                .and(TransactionFilterSpecifications.betweenPrice(transactionFilterDTO.getMinPrice(), transactionFilterDTO.getMaxPrice()))
                .and(TransactionFilterSpecifications.createdBetween(transactionFilterDTO.getAfterDate(), transactionFilterDTO.getBeforeDate()));
        List<Transaction> transactions = new ArrayList<>();
        if (transactionFilterDTO.getOrderType() == null  || transactionFilterDTO.getOrder() == null) {
            transactions = transactionRepository.findAll(transactionSpecification);
        }
        else {
            Sort sort = Sort.by(transactionFilterDTO.getOrder().equals(FilterEnum.Order.ASCENDING)
                            ? Sort.Direction.ASC : Sort.Direction.DESC,
                    transactionFilterDTO.getOrderType().getPropertyName());
            transactions = transactionRepository.findAll(transactionSpecification, sort);
        }

        List<GetAllTransactionsResponseDTO> getAllTransactionsResponseDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            GetAllTransactionsResponseDTO transactionsResponseDTO = new GetAllTransactionsResponseDTO();
            transactionsResponseDTO.setId(transaction.getId());
            transactionsResponseDTO.setProductName(transaction.getStock().getProduct().getName());
            transactionsResponseDTO.setQuantity(transaction.getQuantity());
            transactionsResponseDTO.setTransactionType(transaction.getTransactionType());
            transactionsResponseDTO.setCustomerName("");
            if (transaction.getCustomer() != null){
                transactionsResponseDTO.setCustomerName(transaction.getCustomer().getName());
            }

            transactionsResponseDTO.setTotalPrice(transaction.getTotalPrice());
            transactionsResponseDTO.setTransactionDate(transaction.getTransactionDate());
            getAllTransactionsResponseDTOs.add(transactionsResponseDTO);
        }
        return getAllTransactionsResponseDTOs;
    }


    @Override
    @Transactional
    public void addMultipleTransactions(AddMultipleTransactionsRequestDTO addMultipleTransactionsRequestDTO) {
        Set<AddTransactionRequestDTO> addTransactionRequestDTOS = addMultipleTransactionsRequestDTO.getTransactions();


        /*Set<AddTransactionByProductNameRequestDTO> addTransactionByProductNameRequestDTOs = transactions.stream()
                .filter(AddTransactionByProductNameRequestDTO.class::isInstance)
                .map(AddTransactionByProductNameRequestDTO.class::cast)
                .collect(Collectors.toSet());
        Set<AddTransactionByProductNameRequestDTO> uniqueAddTransactionByProductNameRequestDTOs = addTransactionByProductNameRequestDTOs.stream()
                .collect(Collectors.toMap(
                        dto -> dto.getProduct_name() + "|" + dto.getTransactionType(), // Key: productName
                        dto -> dto, // Value: the DTO object
                        (dto1, dto2) -> { // Merge function for duplicates
                            dto1.setQuantity(dto1.getQuantity() + dto2.getQuantity());
                            return dto1;
                        }
                ))
                .values()
                .stream()
                .collect(Collectors.toSet());
*/


        /*Set<AddTransactionByBarcodeRequestDTO> addTransactionByBarcodeRequestDTOs = transactions.stream()
                .filter(AddTransactionByBarcodeRequestDTO.class::isInstance)
                .map(AddTransactionByBarcodeRequestDTO.class::cast)
                .collect(Collectors.toSet());*/


        // Step 1: Collect unique barcodes, product names, and locations
        Set<String> productNames = new HashSet<>();
        Set<String> barcodes = new HashSet<>();
        Set<String> locationNames = new HashSet<>();

// Process all sets in one pass
        addTransactionRequestDTOS.forEach(addTransactionRequestDTO -> {
            if (addTransactionRequestDTO.getProduct_name() != null) {
                productNames.add(addTransactionRequestDTO.getProduct_name());
            }
            if (addTransactionRequestDTO.getBarcode() != null) {
                barcodes.add(addTransactionRequestDTO.getBarcode());
            }
            if (addTransactionRequestDTO.getLocationName() != null) {
                locationNames.add(addTransactionRequestDTO.getLocationName());
            }
        });

// Step 2: Perform batch retrieval for products and locations
        Map<String, Product> barcodeToProductMap = productService.findProductsByBarcodes(barcodes);
        Map<String, Product> productNameToProductMap = productService.findProductsByNames(productNames);
        Map<String, Location> locationNameToLocationMap = locationService.findLocationsByNames(locationNames);

// Step 3: Pre-fetch stock for all product-location combinations
        Set<StockKey> stockKeys = addTransactionRequestDTOS.stream()
                .map(dto -> StockKey.builder()
                        .productKey(ProductKey.builder()
                                .barcode(dto.getBarcode())
                                .name(dto.getProduct_name())
                                .build())
                        .locationKey(LocationKey.builder()
                                .name(dto.getLocationName())
                                .build())
                        .build())
                .collect(Collectors.toSet());

        Map<StockKey, Stock> stockMap = stockService.findStocksByStockKeys(stockKeys);

// Step 4: Group the entries
        Set<AddTransactionRequestDTO> uniqueAddTransactionRequestDTOS = addTransactionRequestDTOS.stream()
                .collect(Collectors.toMap(
                        dto -> {
                            // Resolve product by barcode or product name
                            Product product = null;
                            if (dto.getBarcode() != null) {
                                product = barcodeToProductMap.get(dto.getBarcode());
                            }
                            if (product == null && dto.getProduct_name() != null) {
                                product = productNameToProductMap.get(dto.getProduct_name());
                            }
                            if (product == null) {
                                throw new EntityNotFoundException("Given barcode or product not found for: " + dto);
                            }
                            // Create a unique key using resolved product ID
                            Long productId = product.getId();
                            return productId + "|" + dto.getTransactionType() + "|" + dto.getLocationName();
                        },
                        dto -> dto,
                        (dto1, dto2) -> {
                            dto1.setQuantity(dto1.getQuantity() + dto2.getQuantity());
                            return dto1;
                        }
                ))
                .values()
                .stream()
                .collect(Collectors.toSet());

// Step 5: Map grouped DTOs to transactions
        Set<Transaction> transactions = uniqueAddTransactionRequestDTOS.stream()
                .map(entry -> {
                    Product product = null;
                    if (entry.getBarcode() != null) {
                        product = barcodeToProductMap.get(entry.getBarcode());
                    }
                    if (product == null && entry.getProduct_name() != null) {
                        product = productNameToProductMap.get(entry.getProduct_name());
                    }
                    if (product == null) {
                        throw new EntityNotFoundException("Given barcode or product not found for: " + entry);
                    }

                    ProductEnumeration.UnitType unitTypeOfProduct = product.getUnitType();
                    if (unitTypeOfProduct == ProductEnumeration.UnitType.COUNT && entry.getQuantity() % 1 != 0) {
                        throw new IllegalArgumentException("Cannot buy/sell COUNT unit type product " + product.getName() + " with decimal values.");
                    }

                    Location location = locationNameToLocationMap.get(entry.getLocationName());
                    if (location == null) {
                        throw new EntityNotFoundException("Location not found: " + entry.getLocationName());
                    }

                    StockKey stockKey = StockKey.builder()
                            .productKey(ProductKey.builder()
                                    .barcode(entry.getBarcode())
                                    .name(entry.getProduct_name())
                                    .build())
                            .locationKey(LocationKey.builder()
                                    .name(entry.getLocationName())
                                    .build())
                            .build();

                    Stock stock = stockMap.get(stockKey);
                    if (stock == null) {
                        throw new EntityNotFoundException("Stock not found for: " + stockKey);
                    }

                    if (entry.getTransactionType() == TransactionEnumeration.TransactionType.SELL && stock.getQuantity() < entry.getQuantity()) {
                        throw new IllegalArgumentException("Not enough stock available for " + product.getName() +
                                " at location " + stock.getLocation().getName() + ". Available: " + stock.getQuantity() + ", Requested: " + entry.getQuantity());
                    }

                    Customer customer = null;
                    if (entry.getCustomer_name() != null) {
                        customer = customerService.findCustomerByCustomerKey(CustomerKey.builder()
                                .name(entry.getCustomer_name())
                                .build());
                    }

                    return Transaction.builder()
                            .transactionType(entry.getTransactionType())
                            .stock(stock)
                            .totalPrice(entry.getTotalPrice())
                            .quantity(entry.getQuantity())
                            .customer(customer)
                            .build();
                })
                .collect(Collectors.toSet());

// Step 6: Save transactions
        transactionRepository.saveAll(transactions);
















        /*for (AddTransactionRequestDTO addTransactionRequestDTO : addTransactionRequestDTOList) {
            if (addTransactionRequestDTO instanceof AddTransactionByBarcodeRequestDTO) {
                this.addTransactionByBarcode((AddTransactionByBarcodeRequestDTO) addTransactionRequestDTO);
            } else if (addTransactionRequestDTO instanceof AddTransactionByProductNameRequestDTO) {
                this.addTransactionByProductName((AddTransactionByProductNameRequestDTO) addTransactionRequestDTO);
            }
        }*/

    }



    /*@Transactional
    @Override
    public double calculateQuantity(long id) {
        List<Transaction> transactions = transactionRepository.findByProduct_Id(id);
        double totalQuantity = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionEnumeration.TransactionType.SELL)
                totalQuantity -= transaction.getQuantity();
            else
                totalQuantity += transaction.getQuantity();
        }
        return totalQuantity;
    }*/
}
