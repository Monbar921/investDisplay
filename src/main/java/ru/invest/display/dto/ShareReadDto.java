package ru.invest.display.dto;

public record ShareReadDto(String name, double price, double quantity, String platform,
                           String code, String country, String sector){
}
