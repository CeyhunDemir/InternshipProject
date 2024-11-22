package com.sd.stockmanagementsystem.application.dto.valid;

import com.sd.stockmanagementsystem.application.dto.validators.ValidQuantityOfUnitTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidQuantityOfUnitTypeValidator.class})
@Documented
public @interface ValidQuantityOfUnitType {
    String message() default "{ValidQuantityOfUnitType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
