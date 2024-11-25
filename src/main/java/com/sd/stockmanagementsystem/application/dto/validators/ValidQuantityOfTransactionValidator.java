package com.sd.stockmanagementsystem.application.dto.validators;

import com.sd.stockmanagementsystem.application.dto.valid.ValidQuantityOfTransaction;
import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.service.ITransactionService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidQuantityOfTransactionValidator implements ConstraintValidator<ValidQuantityOfTransaction, Object> {
    private final ITransactionService transactionService;
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            var productField = o.getClass().getDeclaredField("product");
            var transactionQuantityField = o.getClass().getDeclaredField("quantity");
            var transactionTypeField = o.getClass().getDeclaredField("transactionType");

            productField.setAccessible(true);
            transactionQuantityField.setAccessible(true);
            transactionTypeField.setAccessible(true);

            var productObj = productField.get(o);
            Product product = (Product) productObj;
            double transactionQuantity = (double) transactionQuantityField.get(o);
            double quantityInStock = transactionService.calculateQuantity(product.getId());
            TransactionEnumeration.TransactionType transactionType = (TransactionEnumeration.TransactionType) transactionTypeField.get(o);

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
