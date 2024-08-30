package ru.invest.display.dto;

import java.time.LocalDate;

public record BankAccountCreateDto(ProductCreateDto product,
                                   double interest, LocalDate endDate, String bank){
}
