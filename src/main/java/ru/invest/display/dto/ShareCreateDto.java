package ru.invest.display.dto;
public record ShareCreateDto(
        ProductCreateDto product,
        String code,
        String country,
        String sector
) {

}