package ru.invest.display.mapper;

import org.mapstruct.Mapper;


public interface GeneralMapper<F, T> {
        T map(F source);
}
