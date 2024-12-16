package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.User;

@Mapper(componentModel = "spring")
public interface UserReadMapper extends GeneralMapper<User, UserReadDto>{
    UserReadDto map(User source);
}
