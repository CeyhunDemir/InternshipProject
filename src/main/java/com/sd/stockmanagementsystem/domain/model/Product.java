package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.ProductEnumeration.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "product_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "product_name", unique = true, nullable = false)
    private String name;

    @Column(name = "product_original_name", unique = false, nullable = true)
    private String originalName;

    @Column(name = "product_unit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "product_quantity")
    private double quantity;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_barcode", unique = true, nullable = false)
    private String barcode;

    @Column(name = "product_enabled")
    private boolean enabled;

    @Column(name = "product_created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "product_updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Stock> Stocks;

}
