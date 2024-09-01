package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.dto.HouseReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.House;

@Mapper(componentModel = "spring")
public interface HouseReadMapper extends GeneralMapper<House, HouseReadDto>{
    @Mapping(source = "source.name", target = "product.name")
    @Mapping(source = "source.price", target = "product.price")
    @Mapping(source = "source.quantity", target = "product.quantity")
    @Mapping(source = "source.startDate", target = "product.startDate")
    @Mapping(source = "source.user.username", target = "product.user.username")
    HouseReadDto map(House source);
}
