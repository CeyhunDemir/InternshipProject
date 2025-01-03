package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "value")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "value_id_generator")
    @SequenceGenerator(name = "value_id_generator", sequenceName = "value_sequence", allocationSize = 1)
    @Column(name = "value_id", updatable = false, nullable = false)
    private long id;

    @Column(name = "value_value")
    private String value;
}
