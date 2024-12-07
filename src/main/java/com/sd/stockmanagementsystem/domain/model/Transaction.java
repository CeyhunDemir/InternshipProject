package com.sd.stockmanagementsystem.domain.model;

import com.sd.stockmanagementsystem.domain.enumeration.TransactionEnumeration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "transaction")
@Data
@Builder
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
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @Column(name = "transaction_transactionDate")
    @CreationTimestamp
    private Instant transactionDate;

    @Column(name = "transaction_quantity")
    private double quantity;

    @Column(name = "transaction_transactionType")
    @Enumerated(EnumType.STRING)
    private TransactionEnumeration.TransactionType transactionType;

    @Column(name = "transaction_totalPrice")
    private double totalPrice;

    @Column(name = "transaction_createdAt")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "transaction_updatedAt")
    @UpdateTimestamp
    private Instant updatedAt;
}
