package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class ShareService extends ProductService<Long, Share>{
    private final GeneralMapper<ShareCreateDto, Share> shareCreateMapper;

    public ShareService(@Autowired ShareRepository shareRepository, @Autowired UserService userService
            , @Autowired GeneralMapper<ShareCreateDto, Share> shareCreateMapper) {
        super.setRepository(shareRepository);
        super.setUserService(userService);
        this.shareCreateMapper = shareCreateMapper;
    }

    @Transactional
    public Long create(ShareCreateDto shareDto, User user) {
        // validation
        var entity = shareCreateMapper.map(shareDto);
        return super.create(entity, user);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<Share, T> shareMapper) {
        return getRepository().findById(id)
                .map(shareMapper::map);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<Share, T> shareMapper) {
        return ((ShareRepository)getRepository()).findByName(name)
                .map(shareMapper::map);
    }

    @Transactional
    public Optional<Share> findByCode(String code) {
        return ((ShareRepository)getRepository()).findByCode(code);
    }

    @Transactional
    public <T> Optional<T> findByCode(String code, GeneralMapper<Share, T> shareMapper) {
        return ((ShareRepository)getRepository()).findByCode(code)
                .map(shareMapper::map);
    }
}
