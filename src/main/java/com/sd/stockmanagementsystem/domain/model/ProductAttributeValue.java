package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productAttributeValue")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productAttributeValue_id_generator")
    @SequenceGenerator(name = "productAttributeValue_id_generator", sequenceName = "productAttributeValue_sequence", allocationSize = 1)
    @Column(name = "product_attribute_value_id", nullable = false, updatable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "attribute_value_id", nullable = false)
    private AttributeValue attributeValue;
}
