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
public class UpdateCustomerRequestDTO {
    @NotNull(message = "Customer id can not be null")
    private long id;
    @NotNull(message = "Customer name can not be null!")
    @NotBlank(message = "Name can not be blank!")
    @Size(min = 2, max = 100, message = "Customer name has to be between 2 and 100 characters.")
    private String name;


    @Size(min = 2, max = 200, message = "Customer address has to be between 2 and 200 characters.")
    private String address;


    @Email(message = "Email should be valid.")
    private String email;


    @Size(min = 12, max = 12, message = "Please type the phone number in 90 505... format")
    //TODO Phone validator
    private String phone;
}
