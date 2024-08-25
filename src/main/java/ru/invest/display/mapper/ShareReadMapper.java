package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Mapper(componentModel = "spring")
public interface ShareReadMapper extends GeneralMapper<Share, ShareReadDto>{
    ShareReadDto map(Share source);
}
