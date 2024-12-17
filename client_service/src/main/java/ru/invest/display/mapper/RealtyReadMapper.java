package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.RealtyReadDto;
import ru.invest.display.entity.Realty;

@Mapper(componentModel = "spring")
public interface RealtyReadMapper extends GeneralMapper<Realty, RealtyReadDto>{
    @Mapping(source = "source.name", target = "product.name")
    @Mapping(source = "source.buyPrice", target = "product.buyPrice")
    @Mapping(source = "source.currentPrice", target = "product.currentPrice")
    @Mapping(source = "source.quantity", target = "product.quantity")
    @Mapping(source = "source.startDate", target = "product.startDate")
    RealtyReadDto map(Realty source);
}
