package ru.invest.display.utils;

import ru.invest.display.dto.ProductCreateDto;

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
            if (dto.user() == null) {
                throw new IllegalArgumentException("User is not provided");
            } else {
                if (dto.user().username() == null) {
                    throw new IllegalArgumentException("Username is not provided");
                }
            }
        }
    }
}
