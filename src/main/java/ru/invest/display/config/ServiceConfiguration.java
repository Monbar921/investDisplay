package ru.invest.display.config;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.User;
import ru.invest.display.interceptor.TransactionInterceptor;

import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.service.ShareService;
import ru.invest.display.service.UserService;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class ServiceConfiguration {
    @Bean
    public UserService userService(@Autowired TransactionInterceptor transactionInterceptor
            , @Autowired UserRepository userRepository
            , @Autowired GeneralMapper<UserCreateDto, User> userCreateMapper) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(UserService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(UserService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(UserRepository.class, GeneralMapper.class)
                .newInstance(userRepository, userCreateMapper);
    }

    @Bean
    public ShareService shareService(@Autowired TransactionInterceptor transactionInterceptor
            , @Autowired ShareRepository shareRepository
            , @Autowired GeneralMapper<ShareCreateDto, User> shareCreateMapper) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(ShareService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(ShareService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(ShareRepository.class, GeneralMapper.class)
                .newInstance(shareRepository, shareCreateMapper);
    }
}
