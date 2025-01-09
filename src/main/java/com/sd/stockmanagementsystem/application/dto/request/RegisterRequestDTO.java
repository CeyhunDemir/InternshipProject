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
/*@ValidUniqueEmail(message = "You can not register with this credentials.")*/
public class RegisterRequestDTO {
    @NotNull(message = "First name can not be null.")
    @NotBlank(message = "First name can not be blank.")
    private String firstName;
    @NotNull(message = "Last name can not be null.")
    @NotBlank(message = "Last name can not be blank.")
    private String lastName;
    @Email(message = "Email should be valid.")
    @NotNull(message = "Email can not be null.")
    @NotBlank(message = "Email can not be blank.")
    private String email;
    @NotNull(message = "Password can not be null.")
    @NotBlank(message = "Password can not be blank.")
    @Size(min = 10,max = 50, message = "Password must be between 10 and 50 characters")
    private String password;
}
