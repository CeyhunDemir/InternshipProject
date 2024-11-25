package com.sd.stockmanagementsystem.application.dto.valid;


import com.sd.stockmanagementsystem.application.dto.validators.ValidQuantityOfProductValidator;
import com.sd.stockmanagementsystem.application.dto.validators.ValidQuantityOfTransactionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidQuantityOfProductValidator.class})
@Documented
public @interface ValidQuantityOfProduct {
    String message() default "{ValidQuantityOfProduct.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
