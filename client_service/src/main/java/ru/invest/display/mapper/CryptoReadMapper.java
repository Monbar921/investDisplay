package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.CryptoReadDto;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.entity.Crypto;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface CryptoReadMapper extends GeneralMapper<Crypto, CryptoReadDto>{
    @Mapping(source = "source.name", target = "product.name")
    @Mapping(source = "source.buyPrice", target = "product.buyPrice")
    @Mapping(source = "source.currentPrice", target = "product.currentPrice")
    @Mapping(source = "source.quantity", target = "product.quantity")
    @Mapping(source = "source.startDate", target = "product.startDate")
    CryptoReadDto map(Crypto source);
}
