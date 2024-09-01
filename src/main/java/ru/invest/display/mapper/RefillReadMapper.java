package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.HouseReadDto;
import ru.invest.display.dto.RefillReadDto;
import ru.invest.display.entity.House;
import ru.invest.display.entity.Refill;

@Mapper(componentModel = "spring")
public interface RefillReadMapper extends GeneralMapper<Refill, RefillReadDto>{
    RefillReadDto map(Refill source);
}
