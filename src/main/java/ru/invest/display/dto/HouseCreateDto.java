package ru.invest.display.dto;

import java.time.LocalDate;

public record HouseCreateDto(ProductCreateDto product,
                             boolean isCommercial, double rent){
}
