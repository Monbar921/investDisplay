package ru.invest.display.dto;
public record ProductCreateDto(String name, double price, double quantity, String platform, UserCreateDto user) {
}
