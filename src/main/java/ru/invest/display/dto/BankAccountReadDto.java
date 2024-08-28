package ru.invest.display.dto;

import java.time.LocalDate;

public record BankAccountReadDto(ProductReadDto productReadDto,
                                 double interest, LocalDate startDate, LocalDate endDate){
}
