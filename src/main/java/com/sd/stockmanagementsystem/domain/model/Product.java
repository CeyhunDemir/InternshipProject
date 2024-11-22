package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.ModelEnumeration.unitType;
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

/*
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
*/

    @Column(name = "product_quantity")
    private double quantity;

    @Column(name = "product_unitType")
    private unitType unitType;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_createdAt")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "product_updatedAt")
    @UpdateTimestamp
    private Instant updatedAt;

}
