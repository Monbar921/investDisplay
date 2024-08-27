package ru.invest.display.dto;

import java.time.LocalDate;

public record BankAccountCreateDto(String name, double price, double quantity, String platform,
                                   double interest, LocalDate startDate, LocalDate endDate){
}
