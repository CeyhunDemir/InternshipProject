package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfProduct;
import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidQuantityOfProductValidator implements ConstraintValidator<ValidQuantityOfProduct, Object> {
    private final IProductService productService;
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try{
            /*var productField = o.getClass().getDeclaredField("product");
            productField.setAccessible(true);*/

            var product_idField = o.getClass().getDeclaredField("product_id");
            product_idField.setAccessible(true);
            Product product = productService.getProductById(product_idField.getLong(o));

            var quantityField = o.getClass().getDeclaredField("quantity");
            quantityField.setAccessible(true);
            var quantity = quantityField.get(o);

            ProductEnumeration.UnitType unitTypeOfProduct = (product.getUnitType());


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
