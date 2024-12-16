package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dao.HouseRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.HouseCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.House;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class HouseService extends ProductService<Long, House>{
    private final GeneralMapper<HouseCreateDto, House> houseCreateMapper;

    public HouseService(@Autowired HouseRepository houseRepository, @Autowired UserService userService
                              , @Autowired GeneralMapper<HouseCreateDto, House> houseCreateMapper) {
        super.setRepository(houseRepository);
        super.setUserService(userService);
        this.houseCreateMapper = houseCreateMapper;
    }

    @Transactional
    public Long create(HouseCreateDto houseCreateDto) {
        // validation
        var entity = houseCreateMapper.map(houseCreateDto);
        return super.create(entity);
    }


    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<House, T> houseMapper) {
        return super.getRepository().findById(id)
                .map(houseMapper::map);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<House, T> houseMapper) {
        return super.getRepository().findByName(name)
                .map(houseMapper::map);
    }

}
