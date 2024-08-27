package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T22:07:58+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class ShareCreateMapperImpl implements ShareCreateMapper {

    @Override
    public Share map(ShareCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Share.ShareBuilder<?, ?> share = Share.builder();

        share.user( userCreateDtoToUser( source.user() ) );
        share.code( source.code() );
        share.country( source.country() );
        share.sector( source.sector() );

        return share.build();
    }

    protected User userCreateDtoToUser(UserCreateDto userCreateDto) {
        if ( userCreateDto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( userCreateDto.username() );

        return user.build();
    }
}
