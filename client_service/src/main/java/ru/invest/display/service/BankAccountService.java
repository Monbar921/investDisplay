package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.utils.DtoValidator;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BankAccountService extends ProductService<BankAccount> {
    private final GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper;
    private final GeneralMapper<BankAccount, BankAccountReadDto> accountReadMapper;;


    public BankAccountService(@Autowired BankAccountRepository bankAccountRepository, @Autowired UserService userService
            , @Autowired GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper,
                              @Autowired GeneralMapper<BankAccount, BankAccountReadDto> accountReadMapper) {
        super.setRepository(bankAccountRepository);
        super.setUserService(userService);
        this.accountCreateMapper = accountCreateMapper;
        this.accountReadMapper = accountReadMapper;
    }

    private void validateCreateDto(BankAccountCreateDto accountCreateDto) {
        if (accountCreateDto == null) {
            throw new IllegalArgumentException("Create dto is not provided");
        }
        DtoValidator.validateCreateProductDto(accountCreateDto.product());
        if (accountCreateDto.bank() == null) {
            throw new IllegalArgumentException("Bank is not provided");
        }
    }

    private void validateReadDto(String bank, String accountName) {
        if (bank.isEmpty() || bank.isBlank()) {
            throw new IllegalArgumentException("Bank is not provided");
        }
        if (accountName.isEmpty() || accountName.isBlank()) {
            throw new IllegalArgumentException("AccountName is not provided");
        }
    }

    @Transactional
    public Long create(BankAccountCreateDto accountCreateDto, User user) {
        validateCreateDto(accountCreateDto);
        var entity = accountCreateMapper.map(accountCreateDto);
        return super.create(entity, user);
    }

    @Transactional
    public List<BankAccountReadDto> findBankAccount(String bank, String accountName, User user) {
        validateReadDto(bank, accountName);
        return findByBankAndNameAndUserId(bank, accountName, user);
    }


    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return getRepository().findById(id)
                .map(bankAccountMapper::map);
    }

    @Transactional
    public <T> Optional<T> findByBankAndName(String bank, String name, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return getRepository().findByName(name)
                .map(bankAccountMapper::map);
    }

    @Transactional
    public List<BankAccountReadDto> findByBankAndNameAndUserId(String bank, String name, User user) {
        return ((BankAccountRepository)getRepository()).findByBankAndNameAndUserId(bank, name, user)
                .stream().map(accountReadMapper::map).toList();
    }
}
