package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute_value")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_value_id_generator")
    @SequenceGenerator(name = "attribute_value_id_generator", sequenceName = "attribute_value_sequence", allocationSize = 1)
    @Column(name = "attribute_value_id", nullable = false, updatable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "value_id")
    private Value value;
}
