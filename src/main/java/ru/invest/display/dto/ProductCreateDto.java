package ru.invest.display.dto;

import java.time.LocalDate;

public record ProductCreateDto(String name, double price, double quantity, LocalDate startDate, UserCreateDto user) {
}
