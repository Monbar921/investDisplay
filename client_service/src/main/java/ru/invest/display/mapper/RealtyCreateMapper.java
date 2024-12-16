package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.RealtyCreateDto;
import ru.invest.display.entity.Realty;

@Mapper(componentModel = "spring")
public interface RealtyCreateMapper extends GeneralMapper<RealtyCreateDto, Realty>{
    @Mapping(source = "source.product.name", target = "name")
    @Mapping(source = "source.product.buyPrice", target = "buyPrice")
    @Mapping(source = "source.product.quantity", target = "quantity")
    @Mapping(source = "source.product.startDate", target = "startDate")
    @Mapping(source = "source.product.user.username", target = "user.username")
    Realty map(RealtyCreateDto source);
}
