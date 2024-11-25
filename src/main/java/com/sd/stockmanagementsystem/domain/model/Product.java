package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration.UnitType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_unitType")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "product_quantity")
    private double quantity;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_createdAt")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "product_updatedAt")
    @UpdateTimestamp
    private Instant updatedAt;

}
