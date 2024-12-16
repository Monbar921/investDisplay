package ru.invest.display.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ResponsePostEstateDTO;
import ru.invest.display.service.BankAccountService;

@RestController
@RequestMapping("/api")
public class EstateController {

    private final BankAccountService bankAccountService;

    public EstateController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/estate/bank_account/post")
    public ResponseEntity<ResponsePostEstateDTO> postBankAccount(@RequestBody BankAccountCreateDto bankAccountDto) {
        int status = 200;
        String errorMessage = "ok";

        try {
            bankAccountService.create(bankAccountDto);
        }catch (IllegalArgumentException e) {
            status = 404;
            errorMessage = e.getMessage();
        }
        return ResponseEntity.status(status).body(new ResponsePostEstateDTO(errorMessage));
    }

//    @GetMapping("/estate/bank_account/get/{accountName}")
//    public ResponseEntity<ResponsePostEstateDTO> getBankAccount(@PathVariable String accountName) {
//        int status = 200;
//        String errorMessage = "ok";
//
//        try {
//            bankAccountService.findByName(accountName);
//        }catch (IllegalArgumentException e) {
//            status = 404;
//            errorMessage = e.getMessage();
//        }
//        return ResponseEntity.status(status).body(new ResponsePostEstateDTO(errorMessage));
//    }
}
