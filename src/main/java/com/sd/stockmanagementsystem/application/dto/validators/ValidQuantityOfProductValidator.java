package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
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

            var product_nameField = o.getClass().getDeclaredField("product_name");
            product_nameField.setAccessible(true);
            ProductKey productKey = new ProductKey();
            productKey.setName((String) product_nameField.get(o));
            Product product = productService.findProductByProductKey(productKey);

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
