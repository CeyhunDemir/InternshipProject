package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_generator")
    @SequenceGenerator(name = "transaction_id_generator", sequenceName = "transaction_sequence", allocationSize = 1)
    @Column(name = "transaction_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "transaction_transactionDate")
    private Timestamp transactionDate;

    @Column(name = "transaction_quantity")
    private double quantity;

    @Column(name = "transaction_transactionType")
    @Enumerated(EnumType.STRING)
    private TransactionEnumeration.TransactionType transactionType;

    @Column(name = "transaction_totalPrice")
    private double totalPrice;

    @Column(name = "transaction_createdAt")
    private Timestamp createdAt;
}
