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
        ShareCreateDto share = new ShareCreateDto(
                new ProductCreateDto("Sber", 100, 1, "AlfaInvest", new UserCreateDto("alae"))
                ,"SBRF"
                ,"US"
                ,"FINANCE"
        );

        shareService.create(share);
    }

    @Test
    void testInsertBankAccount() {
        String username = "alae";

        Optional<User> opUser = userService.findByUsername(username);

        if(opUser.isEmpty()){
            UserCreateDto userCreateDto = new UserCreateDto(username);
            Long id = userService.create(userCreateDto);

            User savedUser = User
                    .builder()
                    .id(id)
                    .username(username)
                    .build();

            opUser = Optional.of(savedUser);
        }
//
//
//        BankAccountCreateDto bankAccount = new BankAccountCreateDto(
//                "Alfa account");
//                .price(100)
//                .quantity(1)
//                .platform("Alfa")
//                .user(opUser.get())
//                .interest(10)
//                .startDate(LocalDate.of(2022, Month.DECEMBER, 30))
//                .endDate(LocalDate.of(2023, Month.DECEMBER, 30))
//                .build();
//
//        bankAccountService.create(bankAccount);
    }
}
