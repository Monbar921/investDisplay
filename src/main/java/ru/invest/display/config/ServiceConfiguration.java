package ru.invest.display.config;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.interceptor.TransactionInterceptor;

import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.service.BankAccountService;
import ru.invest.display.service.ShareService;
import ru.invest.display.service.UserService;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class ServiceConfiguration {
    @Bean
    public UserService userService(@Autowired TransactionInterceptor transactionInterceptor
            , @Autowired UserRepository repository
            , @Autowired GeneralMapper<UserCreateDto, User> mapper) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(UserService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(UserService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(UserRepository.class, GeneralMapper.class)
                .newInstance(repository, mapper);
    }

    @Bean
    public ShareService shareService(@Autowired TransactionInterceptor transactionInterceptor
            , @Autowired ShareRepository repository
            , @Autowired UserService userService
            , @Autowired GeneralMapper<ShareCreateDto, Share> mapper)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(ShareService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(ShareService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(ShareRepository.class, UserService.class, GeneralMapper.class)
                .newInstance(repository, userService, mapper);
    }

    @Bean
    public BankAccountService bankAccountService(@Autowired TransactionInterceptor transactionInterceptor
            , @Autowired BankAccountRepository repository
            , @Autowired UserService userService
            , @Autowired GeneralMapper<BankAccountCreateDto, BankAccount> mapper)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(BankAccountService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(BankAccountService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(BankAccountRepository.class, UserService.class, GeneralMapper.class)
                .newInstance(repository, userService, mapper);
    }
}
