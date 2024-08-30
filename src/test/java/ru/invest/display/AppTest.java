package ru.invest.display;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.invest.display.config.HibernateConfiguration;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.*;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.service.BankAccountService;
import ru.invest.display.service.ShareService;
import ru.invest.display.service.UserService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
//@RequiredArgsConstructor
@SpringBootTest
@Slf4j
public class AppTest {
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final ShareService shareService;


    public AppTest(@Autowired UserService userService, @Autowired ShareService shareService,
                   @Autowired BankAccountService bankAccountService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.shareService = shareService;
    }

    @Test
    void testInsertShare() {
        LocalDate start = LocalDate.now();

        ShareCreateDto share = new ShareCreateDto(
                new ProductCreateDto("Sber", 100, 1, start, new UserCreateDto("alae"))
                ,"SBRF"
                ,"US"
                ,"FINANCE",
                "Alfa"
        );

        shareService.create(share);
    }

    @Test
    void testInsertBankAccount() {
        LocalDate start = LocalDate.now();

        BankAccountCreateDto accountCreateDto = new BankAccountCreateDto(
                new ProductCreateDto("Sber", 100, 1, start, new UserCreateDto("alae"))
                , 10
                , start
                ,"Alfa"
        );

        bankAccountService.create(accountCreateDto);
    }
}
