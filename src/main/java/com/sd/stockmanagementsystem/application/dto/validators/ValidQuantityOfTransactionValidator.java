package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfTransaction;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidQuantityOfTransactionValidator implements ConstraintValidator<ValidQuantityOfTransaction, Object> {
    private final ITransactionService transactionService;
    private final IProductService productService;
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            /*var productField = o.getClass().getDeclaredField("product");*/
            /*productField.setAccessible(true);*/
            /*var productObj = productField.get(o);*/
            /*Product product = (Product) productObj;*/

            var transactionQuantityField = o.getClass().getDeclaredField("quantity");
            transactionQuantityField.setAccessible(true);
            double transactionQuantity = (double) transactionQuantityField.get(o);

            var transactionTypeField = o.getClass().getDeclaredField("transactionType");
            transactionTypeField.setAccessible(true);
            TransactionEnumeration.TransactionType transactionType = (TransactionEnumeration.TransactionType) transactionTypeField.get(o);

            var product_idField = o.getClass().getDeclaredField("product_id");
            product_idField.setAccessible(true);
            long product_id = (long) product_idField.get(o);

            double quantityInStock = transactionService.calculateQuantity(product_id);


            if (transactionType == TransactionEnumeration.TransactionType.SELL) {
                return !(quantityInStock < transactionQuantity);
            }
            return true;

        }

        catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
