package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class ShareService {
    private final ShareRepository shareRepository;
    private final GeneralMapper<ShareCreateDto, Share> shareCreateMapper;
    private final UserService userService;

    public ShareService(@Autowired ShareRepository shareRepository, @Autowired UserService userService
                        ,@Autowired GeneralMapper<ShareCreateDto, Share> shareCreateMapper) {
        this.shareRepository = shareRepository;
        this.shareCreateMapper = shareCreateMapper;
        this.userService = userService;
    }

    @Transactional
    public Long create(ShareCreateDto shareDto) {
        Long shareId = null;
        // validation
        var entity = shareCreateMapper.map(shareDto);

        Optional<User> opUser = userService.findByUsername(entity.getUser().getUsername());

        if (opUser.isPresent()){
            entity.getUser().setId(opUser.get().getId());
            opUser = userService.merge(entity.getUser());

            if (opUser.isPresent()){
                entity.setUser(opUser.get());

                shareId = shareRepository.save(entity).getId();
            }
        }

        log.info("user: id = {}, {}", entity.getUser().getId(), entity.getUser());

        return shareId;
    }

    @Transactional
    public Optional<Share> findById(Long id) {
        return shareRepository.findById(id);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<Share, T> shareMapper) {
        return shareRepository.findById(id)
                .map(shareMapper::map);
    }

    @Transactional
    public Optional<Share> findByName(String name) {
        return shareRepository.findByName(name);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<Share, T> shareMapper) {
        return shareRepository.findByName(name)
                .map(shareMapper::map);
    }

    @Transactional
    public Optional<Share> findByCode(String code) {
        return shareRepository.findByCode(code);
    }

    @Transactional
    public <T> Optional<T> findByCode(String code, GeneralMapper<Share, T> shareMapper) {
        return shareRepository.findByCode(code)
                .map(shareMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        var opEntity = shareRepository.findById(id);
        opEntity.ifPresent(entity -> shareRepository.delete(entity.getId()));
        return opEntity.isPresent();
    }
}
