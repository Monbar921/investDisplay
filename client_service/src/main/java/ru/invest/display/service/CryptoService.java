package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.CryptoRepository;
import ru.invest.display.dto.CryptoCreateDto;
import ru.invest.display.entity.Crypto;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class CryptoService extends ProductService<Long, Crypto>{
    private final GeneralMapper<CryptoCreateDto, Crypto> CryptoCreateMapper;

    public CryptoService(@Autowired CryptoRepository CryptoRepository, @Autowired UserService userService
            , @Autowired GeneralMapper<CryptoCreateDto, Crypto> CryptoCreateMapper) {
        super.setRepository(CryptoRepository);
        super.setUserService(userService);
        this.CryptoCreateMapper = CryptoCreateMapper;
    }

    @Transactional
    public Long create(CryptoCreateDto CryptoDto, User user) {
        // validation
        var entity = CryptoCreateMapper.map(CryptoDto);
        return super.create(entity, user);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<Crypto, T> CryptoMapper) {
        return getRepository().findById(id)
                .map(CryptoMapper::map);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<Crypto, T> CryptoMapper) {
        return getRepository().findByName(name)
                .map(CryptoMapper::map);
    }

    @Transactional
    public Optional<Crypto> findByCode(String code) {
        return ((CryptoRepository)getRepository()).findByCode(code);
    }

    @Transactional
    public <T> Optional<T> findByCode(String code, GeneralMapper<Crypto, T> CryptoMapper) {
        return ((CryptoRepository)getRepository()).findByCode(code)
                .map(CryptoMapper::map);
    }
}
