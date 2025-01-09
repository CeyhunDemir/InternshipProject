package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByCustomerKeyResponseDTO;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.CustomerMapper;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final IGeneralMapperService customerMapperService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public void addCustomer(AddCustomerRequestDTO addCustomerRequestDTO) {
        Customer customer = customerMapperService.forRequest().map(addCustomerRequestDTO, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(CustomerKey customerKey) {
        Customer customer = findCustomerByCustomerKey(customerKey);
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        CustomerKey customerKey = customerMapperService.forRequest().map(updateCustomerRequestDTO, CustomerKey.class);
        Customer customer = findCustomerByCustomerKey(customerKey);
        customerMapper.updateCustomerFromDTO(updateCustomerRequestDTO, customer);
        customer.setUpdatedAt(Instant.now());
        customerRepository.save(customer);

    }

    @Override
    public Customer findCustomerByCustomerKey(CustomerKey customerKey) {
        /*        return GenericServiceFuntions.findByKey(customerKey, customerRepository, Customer.class);*/

        if (customerKey == null) {
            throw new EntityNotFoundException("Given Key is empty!");
        }
        if (customerKey.getId() != null) {
            Optional<Customer> customer = customerRepository.findById(customerKey.getId());
            if (customer.isPresent()) {
                return customer.get();
            }
        }
        if (StringUtils.hasText(customerKey.getName())) {
            Optional<Customer> customer = customerRepository.findByName(customerKey.getName());
            if (customer.isPresent()) {
                return customer.get();
            }
        }
        if (customerKey.getId() != null) {
            throw new EntityNotFoundException("Customer id not found with id: " + customerKey.getId());
        }
        if (StringUtils.hasText(customerKey.getName())) {
            throw new EntityNotFoundException("Customer name not found with name: " + customerKey.getName());
        }
        throw new EntityNotFoundException("Customer is not found!");
    }


    @Override
    public List<GetAllCustomersResponseDTO> getAllCustomers() {
        List<Customer> allCustomersList = customerRepository.findAll();
        List<GetAllCustomersResponseDTO> getAllCustomersResponseDTO = new ArrayList<>();
        for (Customer customer : allCustomersList) {
            getAllCustomersResponseDTO.add(customerMapperService.forResponse().map(customer, GetAllCustomersResponseDTO.class));
        }
        return getAllCustomersResponseDTO;
    }

    @Override
    public GetCustomerByCustomerKeyResponseDTO getCustomerByCustomerKey(CustomerKey customerKey) {
        Customer customer = findCustomerByCustomerKey(customerKey);
        GetCustomerByCustomerKeyResponseDTO getCustomerByCustomerKeyResponseDTO = customerMapperService.forResponse().map(customer, GetCustomerByCustomerKeyResponseDTO.class);
        return getCustomerByCustomerKeyResponseDTO;
    }


    @Override
    public List<GetAllCustomersBySubstringResponseDTO> getAllCustomersBySubstring(String subString) {
        List<Customer> allCustomersList = customerRepository.findBySubstring(subString);
        List<GetAllCustomersBySubstringResponseDTO> getAllCustomersBySubstringResponseDTOs = new ArrayList<>();
        for (Customer customer : allCustomersList) {
            getAllCustomersBySubstringResponseDTOs.add(customerMapperService.forResponse().map(customer, GetAllCustomersBySubstringResponseDTO.class));
        }
        return getAllCustomersBySubstringResponseDTOs;
    }
}
