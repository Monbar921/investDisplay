package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.utils.DtoValidator;

import java.util.Optional;

@Slf4j
public class BankAccountService extends ProductService<Long, BankAccount>{
    private final GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper;

    public BankAccountService(@Autowired BankAccountRepository bankAccountRepository, @Autowired UserService userService
                              ,@Autowired GeneralMapper<BankAccountCreateDto, BankAccount> accountCreateMapper) {
        super.setRepository(bankAccountRepository);
        super.setUserService(userService);
        this.accountCreateMapper = accountCreateMapper;
    }

    private void validateCreateDto(BankAccountCreateDto accountCreateDto) {
        if(accountCreateDto == null){
            throw new IllegalArgumentException("Create dto is not provided");
        }
        DtoValidator.validateCreateProductDto(accountCreateDto.product());
        if (accountCreateDto.bank() == null) {
            throw new IllegalArgumentException("Bank is not provided");
        }
    }

    @Transactional
    public Long create(BankAccountCreateDto accountCreateDto) {
        validateCreateDto(accountCreateDto);
        var entity = accountCreateMapper.map(accountCreateDto);
        return super.create(entity);
    }


    @Transactional
    public <T> Optional<T> findById(Long id, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return getRepository().findById(id)
                .map(bankAccountMapper::map);
    }
    @Transactional
    public <T> Optional<T> findByName(String name, GeneralMapper<BankAccount, T> bankAccountMapper) {
        return getRepository().findByName(name)
                .map(bankAccountMapper::map);
    }
}
