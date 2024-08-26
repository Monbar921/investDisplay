package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.entity.Share;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T21:07:56+0700",
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

        share.name( source.name() );
        share.price( source.price() );
        share.quantity( source.quantity() );
        share.platform( source.platform() );
        share.code( source.code() );
        share.country( source.country() );
        share.sector( source.sector() );

        return share.build();
    }
}
