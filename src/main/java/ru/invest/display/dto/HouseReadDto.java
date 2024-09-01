package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseReadDto {
    private ProductReadDto product;
    private boolean isCommercial;
    private double rent;
}
