package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.HouseCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.House;

@Mapper(componentModel = "spring")
public interface HouseCreateMapper extends GeneralMapper<HouseCreateDto, House>{
    @Mapping(source = "source.product.name", target = "name")
    @Mapping(source = "source.product.price", target = "price")
    @Mapping(source = "source.product.quantity", target = "quantity")
    @Mapping(source = "source.product.startDate", target = "startDate")
    @Mapping(source = "source.product.user.username", target = "user.username")
    House map(HouseCreateDto source);
}
