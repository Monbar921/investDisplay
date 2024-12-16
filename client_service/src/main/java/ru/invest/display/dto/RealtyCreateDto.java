package ru.invest.display.dto;

public record RealtyCreateDto(ProductCreateDto product,
                              boolean isCommercial, double rent){
}
