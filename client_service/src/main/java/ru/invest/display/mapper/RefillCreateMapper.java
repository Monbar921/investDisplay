package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.HouseCreateDto;
import ru.invest.display.dto.RefillCreateDto;
import ru.invest.display.entity.House;
import ru.invest.display.entity.Refill;

@Mapper(componentModel = "spring")
public interface RefillCreateMapper extends GeneralMapper<RefillCreateDto, Refill>{
    Refill map(RefillCreateDto source);
}
