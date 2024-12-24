package ru.invest.display.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.dto.ResponseEstateDTO;
import ru.invest.display.entity.User;
import ru.invest.display.service.BankAccountService;
import ru.invest.display.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EstateController {
    private final BankAccountService bankAccountService;
    private final UserService userService;

    public EstateController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @PostMapping("/estate/bank_account/post")
    public ResponseEntity<ResponseEstateDTO<BankAccountReadDto>> saveBankAccount(@RequestBody BankAccountCreateDto bankAccountDto/*, @AuthenticationPrincipal UserDetails user*/) {
        int status = 200;
        String errorMessage = "ok";
        UserDetails user = new org.springframework.security.core.userdetails.User(
                "denkor",
                "null",
                new ArrayList<>()
        );
        try {
            User userEntity = userService.findByUsername(user.getUsername()).orElseThrow();
            bankAccountService.create(bankAccountDto, userEntity);
        } catch (IllegalArgumentException e) {
            status = 404;
            errorMessage = e.getMessage();
        }
        return ResponseEntity.status(status).body(new ResponseEstateDTO<>(errorMessage, null));
    }

    @GetMapping("/estate/bank_account/get/{bank}/{accountName}")
    public ResponseEntity<ResponseEstateDTO<BankAccountReadDto>> getBankAccount(@PathVariable String bank, @PathVariable String accountName/*, @AuthenticationPrincipal UserDetails user*/) {
        int status = 200;
        String errorMessage = "ok";

        List<BankAccountReadDto> resultList = new ArrayList<>();
        UserDetails user = new org.springframework.security.core.userdetails.User(
                "denkor",
                "null",
                new ArrayList<>()
        );
        try {
            User userEntity = userService.findByUserDetails(user).orElseThrow();
            List<BankAccountReadDto> entities = bankAccountService.findBankAccount(bank, accountName, userEntity);
            resultList.addAll(entities);
        } catch (IllegalArgumentException e) {
            status = 404;
            errorMessage = e.getMessage();
        }
        return ResponseEntity.status(status).body(new ResponseEstateDTO<>(errorMessage, resultList));
    }
}
