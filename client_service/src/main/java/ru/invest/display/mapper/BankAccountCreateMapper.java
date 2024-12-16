package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface BankAccountCreateMapper extends GeneralMapper<BankAccountCreateDto, BankAccount>{
    @Mapping(source = "source.product.name", target = "name")
    @Mapping(source = "source.product.buyPrice", target = "buyPrice")
    @Mapping(source = "source.product.currentPrice", target = "buyPrice")
    @Mapping(source = "source.product.quantity", target = "quantity")
    @Mapping(source = "source.product.startDate", target = "startDate")
    @Mapping(source = "source.product.user.username", target = "user.username")
    BankAccount map(BankAccountCreateDto source);
}
