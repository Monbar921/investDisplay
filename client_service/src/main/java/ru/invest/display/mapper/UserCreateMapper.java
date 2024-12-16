package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.User;

@Mapper(componentModel = "spring")
public interface UserCreateMapper extends GeneralMapper<UserCreateDto, User>{
    User map(UserCreateDto source);
}
