package ru.invest.display.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.ProductCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T17:24:40+0700",
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

        share.user( productCreateDtoToUser( source.product() ) );
        share.name( sourceProductName( source ) );
        share.price( sourceProductPrice( source ) );
        share.quantity( sourceProductQuantity( source ) );
        share.startDate( sourceProductStartDate( source ) );
        share.code( source.code() );
        share.country( source.country() );
        share.sector( source.sector() );
        share.broker( source.broker() );

        return share.build();
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

    private String sourceProductName(ShareCreateDto shareCreateDto) {
        ProductCreateDto product = shareCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.name();
    }

    private double sourceProductPrice(ShareCreateDto shareCreateDto) {
        ProductCreateDto product = shareCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.price();
    }

    private double sourceProductQuantity(ShareCreateDto shareCreateDto) {
        ProductCreateDto product = shareCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.quantity();
    }

    private LocalDate sourceProductStartDate(ShareCreateDto shareCreateDto) {
        ProductCreateDto product = shareCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.startDate();
    }
}
