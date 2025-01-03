package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.request.*;
import com.sd.stockmanagementsystem.application.dto.response.GetAllTransactionsResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.FilterEnum;
import com.sd.stockmanagementsystem.domain.model.Transaction;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.TransactionRepository;
import com.sd.stockmanagementsystem.infrastructure.config.TransactionFilterSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final IGeneralMapperService transactionMapperService;
    private final IProductService productService;
    private final ICustomerService customerService;

    @Override
    @Transactional
    public void addTransactionByProductName(AddTransactionByProductNameRequestDTO addTransactionByProductNameRequestDTO) {
        Transaction transaction = Transaction.builder()
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
        transactionRepository.save(transaction);
    }

    @Override
    public List<GetAllTransactionsResponseDTO> getAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<GetAllTransactionsResponseDTO> getAllTransactionsResponseDTOs = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            GetAllTransactionsResponseDTO transactionsResponseDTO = new GetAllTransactionsResponseDTO();
            transactionsResponseDTO.setId(transaction.getId());
            transactionsResponseDTO.setProductName(transaction.getProduct().getName());
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
            transactionsResponseDTO.setProductName(transaction.getProduct().getName());
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
    public void addTransactionByBarcode(AddTransactionByBarcodeRequestDTO addTransactionByBarcodeRequestDTO) {

    }

    @Override
    @Transactional
    public void addMultipleTransactions(List<AddTransactionRequestDTO> addTransactionRequestDTOList) {
        for (AddTransactionRequestDTO addTransactionRequestDTO : addTransactionRequestDTOList) {
            if (addTransactionRequestDTO instanceof AddTransactionByBarcodeRequestDTO) {
                this.addTransactionByBarcode((AddTransactionByBarcodeRequestDTO) addTransactionRequestDTO);
            } else if (addTransactionRequestDTO instanceof AddTransactionByProductNameRequestDTO) {
                this.addTransactionByProductName((AddTransactionByProductNameRequestDTO) addTransactionRequestDTO);
            }
        }
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
