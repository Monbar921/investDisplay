package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.BankAccountCreateMapper;
import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.utils.DtoValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BankAccountService extends ProductService<Long, BankAccount> {
    private final GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper;
    private final GeneralMapper<BankAccount, BankAccountReadDto> accountReadMapper;

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

    private void validateReadDto(BankAccountReadDto accountReadDto) {
        if (accountReadDto == null) {
            throw new IllegalArgumentException("Read dto is not provided");
        }
        DtoValidator.validateReadProductDto(accountReadDto.getProduct());
        if (accountReadDto.getBank() == null) {
            throw new IllegalArgumentException("Bank is not provided");
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
        List<BankAccountReadDto> result = new ArrayList<>();
        if (bank == null) {
            throw new IllegalArgumentException("Bank is not provided");
        }
        if (accountName == null) {
            throw new IllegalArgumentException("AccountName is not provided");
        }
        super.getUserService().validateUser(user);

        var opBankAccountReadDto = findByBankAndNameAndUserId(bank, accountName, user.getId());

        opBankAccountReadDto.ifPresent(result::add);

        return result;
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
    public Optional<BankAccountReadDto> findByBankAndNameAndUserId(String bank, String name, Long userId) {
        return ((BankAccountRepository)getRepository()).findByBankAndNameAndUserId(bank, name, userId)
                .map(accountReadMapper::map);
    }
}
