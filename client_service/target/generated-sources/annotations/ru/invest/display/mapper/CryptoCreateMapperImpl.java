package ru.invest.display.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.CryptoCreateDto;
import ru.invest.display.dto.ProductCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.Crypto;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T17:24:40+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class CryptoCreateMapperImpl implements CryptoCreateMapper {

    @Override
    public Crypto map(CryptoCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Crypto.CryptoBuilder<?, ?> crypto = Crypto.builder();

        crypto.user( productCreateDtoToUser( source.product() ) );
        crypto.name( sourceProductName( source ) );
        crypto.price( sourceProductPrice( source ) );
        crypto.quantity( sourceProductQuantity( source ) );
        crypto.startDate( sourceProductStartDate( source ) );
        crypto.code( source.code() );
        crypto.broker( source.broker() );

        return crypto.build();
    }

    private String productCreateDtoUserUsername(ProductCreateDto productCreateDto) {
        UserCreateDto user = productCreateDto.user();
        if ( user == null ) {
            return null;
        }
        return user.username();
    }

    protected User productCreateDtoToUser(ProductCreateDto productCreateDto) {
        if ( productCreateDto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( productCreateDtoUserUsername( productCreateDto ) );

        return user.build();
    }

    private String sourceProductName(CryptoCreateDto cryptoCreateDto) {
        ProductCreateDto product = cryptoCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.name();
    }

    private double sourceProductPrice(CryptoCreateDto cryptoCreateDto) {
        ProductCreateDto product = cryptoCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.price();
    }

    private double sourceProductQuantity(CryptoCreateDto cryptoCreateDto) {
        ProductCreateDto product = cryptoCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.quantity();
    }

    private LocalDate sourceProductStartDate(CryptoCreateDto cryptoCreateDto) {
        ProductCreateDto product = cryptoCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.startDate();
    }
}
