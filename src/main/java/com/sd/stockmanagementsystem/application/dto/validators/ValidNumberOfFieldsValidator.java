package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidNumberOfFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ValidNumberOfFieldsValidator implements ConstraintValidator<ValidNumberOfFields, Object> {

    private String[] fields;

    @Override
    public void initialize(ValidNumberOfFields constraintAnnotation) {
        this.fields = constraintAnnotation.fields(); // Get the fields from the annotation
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Iterate through the specified fields
        for (String fieldName : fields) {
            try {
                Field field = value.getClass().getDeclaredField(fieldName);
                field.setAccessible(true); // Access private fields
                Object fieldValue = field.get(value);

                // If any field is not null or empty, return true
                if (fieldValue != null && !fieldValue.toString().trim().isEmpty()) {
                    return true;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle error (optional)
                e.printStackTrace();
            }
        }

        // If none of the specified fields are valid, return false
        return false;
    }
}