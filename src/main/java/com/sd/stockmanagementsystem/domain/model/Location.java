package com.sd.stockmanagementsystem.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_generator")
    @SequenceGenerator(name = "location_id_generator", sequenceName = "location_sequence", allocationSize = 1)
    @Column(name = "location_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "location_name", unique = true)
    private String name;

    @Column(name = "location_address")
    private String address;

    @Column(name = "location_city")
    private String city;

    @Column(name = "location_district")
    private String district;

    @Column(name = "location_country")
    private String country;

    @Column(name = "location_phone")
    private String phone;

}
