package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.ProductReadDto;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-30T22:51:43+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class ShareReadMapperImpl implements ShareReadMapper {

    @Override
    public ShareReadDto map(Share source) {
        if ( source == null ) {
            return null;
        }

        ShareReadDto shareReadDto = new ShareReadDto();

        if ( source.getUser() != null ) {
            if ( shareReadDto.getProduct() == null ) {
                shareReadDto.setProduct( new ProductReadDto() );
            }
            userToProductReadDto( source.getUser(), shareReadDto.getProduct() );
        }
        if ( shareReadDto.getProduct() == null ) {
            shareReadDto.setProduct( new ProductReadDto() );
        }
        shareToProductReadDto( source, shareReadDto.getProduct() );
        shareReadDto.setCode( source.getCode() );
        shareReadDto.setCountry( source.getCountry() );
        shareReadDto.setSector( source.getSector() );
        shareReadDto.setBroker( source.getBroker() );

        return shareReadDto;
    }

    protected void userToUserReadDto(User user, UserReadDto mappingTarget) {
        if ( user == null ) {
            return;
        }

        mappingTarget.setUsername( user.getUsername() );
    }

    protected void userToProductReadDto(User user, ProductReadDto mappingTarget) {
        if ( user == null ) {
            return;
        }

        if ( mappingTarget.getUser() == null ) {
            mappingTarget.setUser( new UserReadDto() );
        }
        userToUserReadDto( user, mappingTarget.getUser() );
    }

    protected void shareToProductReadDto(Share share, ProductReadDto mappingTarget) {
        if ( share == null ) {
            return;
        }

        mappingTarget.setName( share.getName() );
        mappingTarget.setPrice( share.getPrice() );
        mappingTarget.setQuantity( share.getQuantity() );
        mappingTarget.setStartDate( share.getStartDate() );
    }
}
