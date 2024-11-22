package com.sd.stockmanagementsystem.application.dto.valid;

import com.sd.stockmanagementsystem.application.dto.validators.ValidUniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidUniqueEmailValidator.class})
@Documented
public @interface ValidUniqueEmail {
    String message() default "{ValidUniqueEmail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
