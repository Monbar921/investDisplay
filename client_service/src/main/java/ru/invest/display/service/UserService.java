package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final GeneralMapper<UserCreateDto, User> userCreateMapper;

    public UserService(@Autowired UserRepository userRepository, @Autowired GeneralMapper<UserCreateDto, User> userCreateMapper) {
        this.userRepository = userRepository;
        this.userCreateMapper = userCreateMapper;
    }

    @Transactional
    public Long create(UserCreateDto userDto) {
        // validation
        var userEntity = userCreateMapper.map(userDto);
        return userRepository.save(userEntity).getId();
    }

    @Transactional
    public Optional<User> merge(User user) {
        return userRepository.merge(user);
    }


    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<User, T> userMapper) {
        return userRepository.findById(id)
                .map(userMapper::map);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public Optional<User> findByUserDetails(UserDetails user) {
        validateUserDetails(user);
        return userRepository.findByUsername(user.getUsername());
    }

    @Transactional
    public <T> Optional<T> findByUsername(String username, GeneralMapper<User, T> userMapper) {
        return userRepository.findByUsername(username)
                .map(userMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        var opEntity = userRepository.findById(id);
        opEntity.ifPresent(entity -> userRepository.delete(entity.getId()));
        return opEntity.isPresent();
    }

    public void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is not provided");
        }
        if (user.getUsername() == null) {
            throw new IllegalArgumentException("Username is not provided");
        }
    }

    public void validateUserDetails(UserDetails user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User is not found in session");
        }
    }
}
