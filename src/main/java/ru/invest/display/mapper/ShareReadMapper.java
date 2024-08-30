package ru.invest.display.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.invest.display.dto.ShareReadDto;
import ru.invest.display.entity.Share;

@Mapper(componentModel = "spring")
public interface ShareReadMapper extends GeneralMapper<Share, ShareReadDto>{
    @Mapping(source = "source.name", target = "product.name")
    @Mapping(source = "source.price", target = "product.price")
    @Mapping(source = "source.quantity", target = "product.quantity")
    @Mapping(source = "source.startDate", target = "product.startDate")
    @Mapping(source = "source.user.username", target = "product.user.username")
    ShareReadDto map(Share source);
}
