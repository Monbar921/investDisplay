package ru.invest.display.dto;

import java.time.LocalDate;

public record BankAccountReadDto(String name, double price, double quantity, String platform,
                                 double interest, LocalDate startDate, LocalDate endDate){
}
