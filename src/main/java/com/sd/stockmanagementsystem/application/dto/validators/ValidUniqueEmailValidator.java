package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.request.RegisterRequestDTO;
import com.sd.stockmanagementsystem.application.dto.valid.ValidUniqueEmail;
import com.sd.stockmanagementsystem.domain.service.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidUniqueEmailValidator implements ConstraintValidator<ValidUniqueEmail, RegisterRequestDTO> {
    private final IUserService userService;
    @Override
    public boolean isValid(RegisterRequestDTO registerRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.checkUser(registerRequestDTO.getEmail()) == false) {
            return true;
        }
        return false;
    }
}
