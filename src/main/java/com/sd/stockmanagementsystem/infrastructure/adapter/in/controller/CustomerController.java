package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;

import com.sd.stockmanagementsystem.application.dto.request.AddCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.DeleteCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.UpdateCustomerRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersBySubstringResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetAllCustomersResponseDTO;
import com.sd.stockmanagementsystem.application.dto.response.GetCustomerByIdResponseDTO;
import com.sd.stockmanagementsystem.domain.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable(value = "id") long id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerByIdResponseDTO> findProductById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<GetAllCustomersBySubstringResponseDTO>> findCustomerBySearch(@RequestParam String query) {
        return ResponseEntity.ok(customerService.getAllCustomersBySubstring(query));
    }
}
