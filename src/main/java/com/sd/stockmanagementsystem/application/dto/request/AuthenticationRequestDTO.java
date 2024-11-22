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
public class AuthenticationRequestDTO {
    @NotBlank(message = "Email can not be blank.")
    @NotNull(message = "Email can not be null.")
    @Email(message = "Email should be valid.")
    private String email;
    @NotBlank(message = "Password can not be blank.")
    @NotNull(message = "Password can not be null.")
    @Size(min = 10,max = 50, message = "Password must be between 10 and 50 characters")
    private String password;
}
