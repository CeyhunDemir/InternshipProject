package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.Instant;

@StaticMetamodel(Product.class)
public class Product_ {
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, ProductEnumeration.UnitType> unitType;
    public static volatile SingularAttribute<Product, Double> quantity;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Instant> createdAt;
    public static volatile SingularAttribute<Product, Instant> updatedAt;
    public static volatile ListAttribute<Product, Transaction> transactions;
}
