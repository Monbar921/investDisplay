package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.entity.Share;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T21:07:56+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class ShareReadMapperImpl implements ShareReadMapper {

    @Override
    public ShareReadDto map(Share source) {
        if ( source == null ) {
            return null;
        }

        String name = null;
        double price = 0.0d;
        double quantity = 0.0d;
        String platform = null;
        String code = null;
        String country = null;
        String sector = null;

        name = source.getName();
        price = source.getPrice();
        quantity = source.getQuantity();
        platform = source.getPlatform();
        code = source.getCode();
        country = source.getCountry();
        sector = source.getSector();

        ShareReadDto shareReadDto = new ShareReadDto( name, price, quantity, platform, code, country, sector );

        return shareReadDto;
    }
}
