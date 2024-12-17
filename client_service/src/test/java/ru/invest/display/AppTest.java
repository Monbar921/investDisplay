package ru.invest.display;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.invest.display.dto.*;
import ru.invest.display.service.BankAccountService;
import ru.invest.display.service.ShareService;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class AppTest {
    private final BankAccountService bankAccountService;
    private final ShareService shareService;


    public AppTest(@Autowired ShareService shareService,
                   @Autowired BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
        this.shareService = shareService;
    }

    @Test
    void testInsertShare() {
        LocalDate start = LocalDate.now();
/*
        ShareCreateDto share = new ShareCreateDto(
                new ProductCreateDto("Sber", 100, 1, start, new UserCreateDto("alae"))
                ,"SBRF"
                ,"US"
                ,"FINANCE",
                "Alfa"
        );

        shareService.create(share);

 */
    }

    @Test
    void testInsertBankAccount() {
        LocalDate start = LocalDate.now();
/*
        BankAccountCreateDto accountCreateDto = new BankAccountCreateDto(
                new ProductCreateDto("Sber", 100, 1, start, new UserCreateDto("alae"))
                , 10
                , start
                ,"Alfa"
        );

        bankAccountService.create(accountCreateDto);

 */
    }
}
