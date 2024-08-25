package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Mapper(componentModel = "spring")
public interface ShareCreateMapper extends GeneralMapper<ShareCreateDto, Share>{
    Share map(ShareCreateDto source);
}
