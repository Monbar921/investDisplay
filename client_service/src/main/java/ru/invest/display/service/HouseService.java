package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.invest.display.dao.RealtyRepository;
import ru.invest.display.dto.RealtyCreateDto;
import ru.invest.display.entity.Realty;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
@Service
public class HouseService extends ProductService<Realty>{
    private final GeneralMapper<RealtyCreateDto, Realty> houseCreateMapper;

    public HouseService(@Autowired RealtyRepository realtyRepository, @Autowired UserService userService
                              , @Autowired GeneralMapper<RealtyCreateDto, Realty> houseCreateMapper) {
        super.setRepository(realtyRepository);
        super.setUserService(userService);
        this.houseCreateMapper = houseCreateMapper;
    }

    @Transactional
    public Long create(RealtyCreateDto realtyCreateDto, User user) {
        // validation
        var entity = houseCreateMapper.map(realtyCreateDto);
        return super.create(entity, user);
    }


    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<Realty, T> houseMapper) {
        return super.getRepository().findById(id)
                .map(houseMapper::map);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<Realty, T> houseMapper) {
        return super.getRepository().findByName(name)
                .map(houseMapper::map);
    }

}
