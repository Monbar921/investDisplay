package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.CryptoCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.entity.Crypto;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface CryptoCreateMapper extends GeneralMapper<CryptoCreateDto, Crypto>{
    @Mapping(source = "source.product.name", target = "name")
    @Mapping(source = "source.product.price", target = "price")
    @Mapping(source = "source.product.quantity", target = "quantity")
    @Mapping(source = "source.product.startDate", target = "startDate")
    @Mapping(source = "source.product.user.username", target = "user.username")
    Crypto map(CryptoCreateDto source);
}
