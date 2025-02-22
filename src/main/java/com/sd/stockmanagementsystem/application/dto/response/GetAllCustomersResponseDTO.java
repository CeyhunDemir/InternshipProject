package com.sd.stockmanagementsystem.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomersResponseDTO {

    private long id;

    private String name;

    private String address;

    private String email;

    private String phone;
}
