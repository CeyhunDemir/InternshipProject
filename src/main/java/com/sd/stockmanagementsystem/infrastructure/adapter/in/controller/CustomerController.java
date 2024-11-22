package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping
    public void addCustomer(@RequestBody @Valid AddCustomerRequestDTO addCustomerRequestDTO) {
        customerService.addCustomer(addCustomerRequestDTO);
    }
    @PutMapping
    public void updateCustomer(@RequestBody @Valid UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        customerService.updateCustomer(updateCustomerRequestDTO);
    }
    @DeleteMapping
    public void deleteCustomer(@RequestBody @Valid DeleteCustomerRequestDTO deleteCustomerRequestDTO) {
        customerService.deleteCustomer(deleteCustomerRequestDTO);
    }
}
