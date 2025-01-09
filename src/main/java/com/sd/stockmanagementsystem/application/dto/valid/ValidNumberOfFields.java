package com.sd.stockmanagementsystem.application.dto.valid;


import com.sd.stockmanagementsystem.application.dto.validators.ValidNumberOfFieldsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidNumberOfFieldsValidator.class)
public @interface ValidNumberOfFields {
    String message() default "At least one of the specified fields must be provided!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // Specify which fields to validate (comma-separated)
    String[] fields();
}
