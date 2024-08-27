package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T22:07:58+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class UserReadMapperImpl implements UserReadMapper {

    @Override
    public UserReadDto map(User source) {
        if ( source == null ) {
            return null;
        }

        String username = null;

        username = source.getUsername();

        UserReadDto userReadDto = new UserReadDto( username );

        return userReadDto;
    }
}
