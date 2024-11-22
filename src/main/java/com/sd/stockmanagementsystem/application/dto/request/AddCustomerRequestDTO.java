package com.sd.stockmanagementsystem.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequestDTO {
    @NotNull(message = "Customer name can not be null!")
    @NotBlank(message = "Customer name can not be blank!")
    @Size(min = 2, max = 100, message = "Customer name has to be between 2 and 100 characters.")
    private String name;

    @NotNull(message = "Customer address can not be null!")
    @NotBlank(message = "Customer address can not be blank!")
    @Size(min = 2, max = 200, message = "Customer address has to be between 2 and 200 characters.")
    private String address;

    @NotNull(message = "Customer email can not be null!")
    @NotBlank(message = "Customer email can not be blank!")
    @Email(message = "Email should be valid.")
    private String email;

    @NotNull(message = "Customer phone number can not be null!")
    @NotBlank(message = "Customer phone number can not be blank!")
    @Size(min = 12, max = 12, message = "Please type the phone number in 90 505... format")
    //TODO Phone validator
    private String phone;
}
