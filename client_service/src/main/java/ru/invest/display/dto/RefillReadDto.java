package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.invest.display.entity.Product;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillReadDto {
    private double amount;
    private LocalDate date;
    private Product<Long> product;
}
