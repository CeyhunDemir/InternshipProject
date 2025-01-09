package com.sd.stockmanagementsystem.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMultipleTransactionsRequestDTO {
    Set<AddTransactionRequestDTO> transactions;
}
