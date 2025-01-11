package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_generator")
    @SequenceGenerator(name = "stock_id_generator", sequenceName = "stock_sequence", allocationSize = 1)
    @Column(name = "stock_id", nullable = false, updatable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "stock_quantity", nullable = false, columnDefinition = "double precision default 0.0")
    private double quantity = 0.0;
}
