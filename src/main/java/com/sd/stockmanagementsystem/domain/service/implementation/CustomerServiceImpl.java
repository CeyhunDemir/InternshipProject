package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByIdResponseDTO;
import com.sd.stockmanagementsystem.domain.model.Customer;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.mapper.IGeneralMapperService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final IGeneralMapperService customerMapperService;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void addCustomer(AddCustomerRequestDTO addCustomerRequestDTO) {
        Customer customer = customerMapperService.forRequest().map(addCustomerRequestDTO, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
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

    @Override
    public Customer findCustomerById(long id) {
        Long cust_id = id;
        if(cust_id == null){
            return null;
        }
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        else{
            throw new EntityNotFoundException("Customer id not found!");
        }
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
    public GetCustomerByIdResponseDTO getCustomerById(long id) {
        Customer customer = findCustomerById(id);
        GetCustomerByIdResponseDTO getCustomerByIdResponseDTO = customerMapperService.forResponse().map(customer, GetCustomerByIdResponseDTO.class);
        return getCustomerByIdResponseDTO;
    }

    @Override
    public Customer findCustomerByName(String name) {
        Optional<Customer> customer = customerRepository.findByName(name);

        if(customer.isPresent()){
            return customer.get();
        }
        else {
            throw new EntityNotFoundException("Customer name not found!");
        }
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
