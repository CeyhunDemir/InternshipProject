package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.Instant;

@StaticMetamodel(Transaction.class)
public class Transaction_ {
    public static volatile SingularAttribute<Transaction, Long> id;
    public static volatile SingularAttribute<Transaction, Product> product;
    public static volatile SingularAttribute<Transaction, Customer> customer;
    public static volatile SingularAttribute<Transaction, Instant> transactionDate;
    public static volatile SingularAttribute<Transaction, Double> quantity;
    public static volatile SingularAttribute<Transaction, TransactionEnumeration.TransactionType> transactionType;
    public static volatile SingularAttribute<Transaction, Double> price;
    public static volatile SingularAttribute<Transaction, Instant> createdAt;
    public static volatile SingularAttribute<Transaction, Instant> updatedAt;

}
