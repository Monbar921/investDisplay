package ru.invest.display.dto;

import ru.invest.display.entity.Product;

import java.time.LocalDate;

public record RefillCreateDto(double amount, LocalDate date, Product<Long> product){
}
