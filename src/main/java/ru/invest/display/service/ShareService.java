package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class ShareService {
    private final ShareRepository shareRepository;
    private final GeneralMapper<UserCreateDto, User> userCreateMapper;

    public ShareService(@Autowired ShareRepository shareRepository, @Autowired GeneralMapper<ShareCreateDto, User> userCreateMapper) {
        this.shareRepository = shareRepository;
        this.userCreateMapper = userCreateMapper;
    }

    @Transactional
    public Long create(UserCreateDto userDto) {
        // validation
        var userEntity = userCreateMapper.map(userDto);
        return shareRepository.save(userEntity).getId();
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return shareRepository.findById(id);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<User, T> userMapper) {
        return shareRepository.findById(id)
                .map(userMapper::map);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return shareRepository.findByUsername(username);
    }

    @Transactional
    public <T> Optional<T> findByUsername(String username, GeneralMapper<User, T> userMapper) {
        return shareRepository.findByUsername(username)
                .map(userMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeUser = shareRepository.findById(id);
        maybeUser.ifPresent(user -> shareRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }
}
