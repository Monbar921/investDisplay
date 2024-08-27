package ru.invest.display.dto;

public record ShareCreateDto (ProductCreateDto product,
                              UserCreateDto user,
                              String code, String country, String sector){
}
