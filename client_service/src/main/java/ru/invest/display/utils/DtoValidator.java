package ru.invest.display.utils;

import ru.invest.display.dto.ProductCreateDto;
import ru.invest.display.dto.ProductReadDto;

public class DtoValidator {
    public static void validateCreateProductDto(ProductCreateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Product fields are not provided");
        } else {
            if (dto.name() == null) {
                throw new IllegalArgumentException("Product name is not provided");
            }
            if (dto.startDate() == null) {
                throw new IllegalArgumentException("Product startDate is not provided");
            }
        }
    }

    public static void validateReadProductDto(ProductReadDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Product fields are not provided");
        } else {
            if (dto.getName() == null) {
                throw new IllegalArgumentException("Product name is not provided");
            }
        }
    }
}
