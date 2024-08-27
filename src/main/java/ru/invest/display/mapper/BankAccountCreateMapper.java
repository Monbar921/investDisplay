package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface BankAccountCreateMapper extends GeneralMapper<BankAccountCreateDto, BankAccount>{
    BankAccount map(BankAccountCreateDto source);
}
