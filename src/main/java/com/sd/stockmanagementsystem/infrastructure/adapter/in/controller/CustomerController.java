package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.core.CustomerKey;
import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByCustomerKeyResponseDTO;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public ResponseEntity<List<GetAllCustomersResponseDTO>> findAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@ModelAttribute CustomerKey customerKey) {
        customerService.deleteCustomer(customerKey);
    }

    @GetMapping("/find")
    public ResponseEntity<GetCustomerByCustomerKeyResponseDTO> findProductById(@ModelAttribute CustomerKey customerKey) {
        return ResponseEntity.ok(customerService.getCustomerByCustomerKey(customerKey));
    }
    @GetMapping("/search")
    public ResponseEntity<List<GetAllCustomersBySubstringResponseDTO>> findCustomerBySearch(@RequestParam String query) {
        return ResponseEntity.ok(customerService.getAllCustomersBySubstring(query));
    }
}
