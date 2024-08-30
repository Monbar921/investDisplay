package ru.invest.display.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ru.invest.display.dao.BankAccountRepository;
import ru.invest.display.dao.Repository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.BaseEntity;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.io.Serializable;
import java.util.Optional;

public abstract class BaseService {

    private UserService userService;


}
