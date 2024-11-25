package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfProduct;
import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidQuantityOfProductValidator implements ConstraintValidator<ValidQuantityOfProduct, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try{
            var productField = o.getClass().getDeclaredField("product");
            productField.setAccessible(true);
            var quantityField = o.getClass().getDeclaredField("quantity");
            quantityField.setAccessible(true);

            ProductEnumeration.UnitType unitTypeOfProduct = ((Product) productField.get(o)).getUnitType();
            var quantity = quantityField.get(o);

            if (unitTypeOfProduct == ProductEnumeration.UnitType.COUNT && quantity instanceof Double) {
                return ((Double) quantity) % 1 == 0;
            }
            return true;
        }

        catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
