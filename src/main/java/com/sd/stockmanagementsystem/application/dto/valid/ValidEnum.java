package com.sd.stockmanagementsystem.application.dto.valid;


import com.sd.stockmanagementsystem.application.dto.validators.ValidEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidEnumValidator.class})
@Documented
public @interface ValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "{ValidEnum.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
