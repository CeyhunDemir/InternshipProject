package com.sd.stockmanagementsystem.application.dto.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationKey {
    private Long id;
    private String name;
}
