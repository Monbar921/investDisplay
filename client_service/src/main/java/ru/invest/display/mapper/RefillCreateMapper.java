package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.RefillCreateDto;
import ru.invest.display.entity.Refill;

@Mapper(componentModel = "spring")
public interface RefillCreateMapper extends GeneralMapper<RefillCreateDto, Refill>{
    Refill map(RefillCreateDto source);
}
