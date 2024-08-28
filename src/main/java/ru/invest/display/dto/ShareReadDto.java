package ru.invest.display.dto;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ShareReadDto(
        ProductReadDto product,
        String code,
        String country,
        String sector
) {

}