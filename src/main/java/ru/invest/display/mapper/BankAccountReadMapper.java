package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface BankAccountReadMapper extends GeneralMapper<BankAccount, BankAccountReadDto>{
    BankAccountReadDto map(BankAccount source);
}
