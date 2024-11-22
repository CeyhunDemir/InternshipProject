package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.ICustomerMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerMapperService customerMapperService;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void addCustomer(AddCustomerRequestDTO addCustomerRequestDTO) {
        Customer customer = customerMapperService.forRequest().map(addCustomerRequestDTO, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(DeleteCustomerRequestDTO deleteCustomerRequestDTO) {
        Optional<Customer> customer = customerRepository.findById(deleteCustomerRequestDTO.getId());
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        }
        else
            throw new EntityNotFoundException("Customer id not found!");

    }

    @Override
    @Transactional
    public void updateCustomer(UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        Optional<Customer> customer = customerRepository.findById(updateCustomerRequestDTO.getId());
        if (customer.isPresent()) {
            Customer updatedCustomer = customerMapperService.forRequest().map(updateCustomerRequestDTO, Customer.class);
            updatedCustomer.setCreatedAt(customer.get().getCreatedAt());
            customerRepository.save(updatedCustomer);
        }
        else
            throw new EntityNotFoundException("Customer id not found!");
    }
}
