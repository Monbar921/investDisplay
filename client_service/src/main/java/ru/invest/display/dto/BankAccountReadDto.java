package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountReadDto{
    private ProductReadDto product;
    private double interest;
    private LocalDate endDate;
    private String bank;
}
