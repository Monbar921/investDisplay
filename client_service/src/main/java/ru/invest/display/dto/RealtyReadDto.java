package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyReadDto {
    private ProductReadDto product;
    private boolean isCommercial;
    private double rent;
}
