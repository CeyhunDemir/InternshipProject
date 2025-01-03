package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_id_generator")
    @SequenceGenerator(name = "attribute_id_generator", sequenceName = "attribute_sequence", allocationSize = 1)
    @Column(name = "attribute_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "attribute_name", unique = true)
    private String attributeName;
}
