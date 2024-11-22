package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "sale")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_id_generator")
    @SequenceGenerator(name = "sale_id_generator", sequenceName = "sale_sequence", allocationSize = 1)
    @Column(name = "sale_id")
    private long id;

    @OneToMany(mappedBy = "id")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "sale_saleDate")
    private Timestamp saleDate;

    @Column(name = "sale_totalPrice")
    private double totalPrice;

    @Column(name = "sale_createdAt")
    private Timestamp createdAt;
}
