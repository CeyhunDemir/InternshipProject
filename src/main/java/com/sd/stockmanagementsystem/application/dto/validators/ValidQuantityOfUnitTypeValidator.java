package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfUnitType;
import com.sd.stockmanagementsystem.domain.enumeration.ModelEnumeration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidQuantityOfUnitTypeValidator implements ConstraintValidator<ValidQuantityOfUnitType, Object> {
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            // Use reflection to access the fields
            var unitTypeField = object.getClass().getDeclaredField("unitType");
            var quantityField = object.getClass().getDeclaredField("quantity");

            unitTypeField.setAccessible(true);
            quantityField.setAccessible(true);

            var unitType = unitTypeField.get(object);
            var quantity = quantityField.get(object);

            // Perform validation logic
            if (unitType == ModelEnumeration.unitType.COUNT && quantity instanceof Double) {
                return ((Double) quantity) % 1 == 0; // Check if the quantity is a whole number
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Validation fails if the fields are missing or inaccessible
            return false;
        }

        return true;
    }
}
