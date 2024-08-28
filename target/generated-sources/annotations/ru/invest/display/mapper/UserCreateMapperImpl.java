package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T22:04:37+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class UserCreateMapperImpl implements UserCreateMapper {

    @Override
    public User map(UserCreateDto source) {
        if ( source == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( source.username() );

        return user.build();
    }
}
