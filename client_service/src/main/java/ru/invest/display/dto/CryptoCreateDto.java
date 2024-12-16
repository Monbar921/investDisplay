package ru.invest.display.dto;
public record CryptoCreateDto(
        ProductCreateDto product,
        String code,
        String broker
) {

}