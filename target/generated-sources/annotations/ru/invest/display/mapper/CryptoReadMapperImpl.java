package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.CryptoReadDto;
import ru.invest.display.dto.ProductReadDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Crypto;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T17:24:40+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class CryptoReadMapperImpl implements CryptoReadMapper {

    @Override
    public CryptoReadDto map(Crypto source) {
        if ( source == null ) {
            return null;
        }

        CryptoReadDto cryptoReadDto = new CryptoReadDto();

        if ( source.getUser() != null ) {
            if ( cryptoReadDto.getProduct() == null ) {
                cryptoReadDto.setProduct( new ProductReadDto() );
            }
            userToProductReadDto( source.getUser(), cryptoReadDto.getProduct() );
        }
        if ( cryptoReadDto.getProduct() == null ) {
            cryptoReadDto.setProduct( new ProductReadDto() );
        }
        cryptoToProductReadDto( source, cryptoReadDto.getProduct() );
        cryptoReadDto.setCode( source.getCode() );
        cryptoReadDto.setBroker( source.getBroker() );

        return cryptoReadDto;
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

    protected void cryptoToProductReadDto(Crypto crypto, ProductReadDto mappingTarget) {
        if ( crypto == null ) {
            return;
        }

        mappingTarget.setName( crypto.getName() );
        mappingTarget.setPrice( crypto.getPrice() );
        mappingTarget.setQuantity( crypto.getQuantity() );
        mappingTarget.setStartDate( crypto.getStartDate() );
    }
}
