package com.sd.stockmanagementsystem.infrastructure.config;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import com.sd.stockmanagementsystem.domain.model.Product_;
import com.sd.stockmanagementsystem.domain.model.Transaction;
import com.sd.stockmanagementsystem.domain.model.Transaction_;
import com.sd.stockmanagementsystem.domain.util.DateConverter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionFilterSpecifications {
    public static Specification<Transaction> createdBetween(String afterDate, String beforeDate) {
        return ((root, query, criteriaBuilder) -> {
            if (afterDate == null && beforeDate == null)
                return null;
            else if (beforeDate == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Transaction_.createdAt),
                        DateConverter.convertStringToInstant(afterDate));
            else if (afterDate == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get(Transaction_.createdAt),
                        DateConverter.convertStringToInstant(beforeDate));
            else
                return criteriaBuilder.between(root.get(Transaction_.createdAt),
                        DateConverter.convertStringToInstant(afterDate),
                        DateConverter.convertStringToInstant(beforeDate));
        });
    }
    public static Specification<Transaction> hasType(TransactionEnumeration.TransactionType type) {
        return ((root, query, criteriaBuilder) -> {
            if (type == null) return null;
            return criteriaBuilder.equal(root.get(Transaction_.transactionType), type);
        });
    }
    public static Specification<Transaction> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.join(Transaction_.product).get(Product_.name)), "%"+name.toLowerCase()+"%");
        });
    }
    public static Specification<Transaction> betweenQuantity(Double minQuantity, Double maxQuantity) {
        return ((root, query, criteriaBuilder) ->{
            if(minQuantity == null && maxQuantity == null)
                return null;
            else if (minQuantity == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get(Transaction_.quantity), maxQuantity);
            else if (maxQuantity == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Transaction_.quantity), minQuantity);
            else
                return criteriaBuilder.between(root.get(Transaction_.quantity), minQuantity, maxQuantity);
        });
    }
    public static Specification<Transaction> betweenPrice(Double minPrice, Double maxPrice) {
        return ((root, query, criteriaBuilder) ->{
            if(minPrice == null && maxPrice == null)
                return null;
            else if (minPrice == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get(Transaction_.quantity), maxPrice);
            else if (maxPrice == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Transaction_.quantity), minPrice);
            else
                return criteriaBuilder.between(root.get(Transaction_.quantity), minPrice, maxPrice);
        });
    }
}