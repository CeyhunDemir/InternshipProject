package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfUnitType;
import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidQuantityOfUnitTypeValidator implements ConstraintValidator<ValidQuantityOfUnitType, Object> {
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            var unitTypeField = object.getClass().getDeclaredField("unitType");
            var quantityField = object.getClass().getDeclaredField("quantity");

            unitTypeField.setAccessible(true);
            quantityField.setAccessible(true);

            var unitType = unitTypeField.get(object);
            var quantity = quantityField.get(object);

            if (unitType == ProductEnumeration.UnitType.COUNT && quantity instanceof Double) {
                return ((Double) quantity) % 1 == 0; // Check if the quantity is a whole number
            }
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
