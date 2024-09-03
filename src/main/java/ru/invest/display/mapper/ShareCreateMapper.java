package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;

@Mapper(componentModel = "spring")
public interface ShareCreateMapper extends GeneralMapper<ShareCreateDto, Share>{
    @Mapping(source = "source.product.name", target = "name")
    @Mapping(source = "source.product.buyPrice", target = "buyPrice")
    @Mapping(source = "source.product.currentPrice", target = "buyPrice")
    @Mapping(source = "source.product.quantity", target = "quantity")
    @Mapping(source = "source.product.startDate", target = "startDate")
    @Mapping(source = "source.product.user.username", target = "user.username")
    Share map(ShareCreateDto source);
}
