package ru.invest.display.dto;

import java.time.LocalDate;

public record BankAccountCreateDto(ProductCreateDto productCreateDto,
                                   double interest, LocalDate startDate, LocalDate endDate){
}
