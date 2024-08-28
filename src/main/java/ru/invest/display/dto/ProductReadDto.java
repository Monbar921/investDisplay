package ru.invest.display.dto;

public record ProductReadDto(String name, double price, double quantity, String platform, UserReadDto user) {
}
