package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ShareCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;
import ru.invest.display.mapper.GeneralMapper;

import java.util.Optional;

@Slf4j
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper;
    private final UserService userService;

    public BankAccountService(@Autowired BankAccountRepository bankAccountRepository, @Autowired UserService userService
                              ,@Autowired GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountCreateMapper = accountCreateMapper;
        this.userService = userService;
    }

    @Transactional
    public Long create(BankAccountCreateDto accountCreateDto) {
        // validation
        var entity = accountCreateMapper.map(accountCreateDto);
        return bankAccountRepository.save(entity).getId();
    }

    @Transactional
    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return bankAccountRepository.findById(id)
                .map(bankAccountMapper::map);
    }

    @Transactional
    public Optional<BankAccount> findByName(String name) {
        return bankAccountRepository.findByName(name);
    }

    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return bankAccountRepository.findByName(name)
                .map(bankAccountMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        var opEntity = bankAccountRepository.findById(id);
        opEntity.ifPresent(entity -> bankAccountRepository.delete(entity.getId()));
        return opEntity.isPresent();
    }

}
