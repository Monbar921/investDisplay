package ru.invest.display.dto;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareReadDto {
    private ProductReadDto product;
    private String code;
    private String country;
    private String sector;
    private String broker;
}