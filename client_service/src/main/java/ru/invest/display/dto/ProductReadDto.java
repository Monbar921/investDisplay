package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReadDto {
    private String name;
    private double buyPrice;
    private double currentPrice;
    private double quantity;
    private LocalDate startDate;
}
