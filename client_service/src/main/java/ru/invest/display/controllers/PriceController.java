package ru.invest.display.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.invest.display.dto.*;
import ru.invest.display.entity.User;
import ru.invest.display.service.CryptoService;
import ru.invest.display.service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PriceController {
    @Value("${ru.share.price.handler.url}")
    private String ruShareHandlerUrl;
    @Value("${en.share.price.handler.url}")
    private String enShareHandlerUrl;
    @Value("${crypto.price.handler.url}")
    private String cryptoHandlerUrl;

    private final RestTemplate restTemplate;
    private final CryptoService cryptoService;
    private final UserService userService;

    public PriceController(RestTemplate restTemplate, CryptoService cryptoService, UserService userService) {
        this.restTemplate = restTemplate;
        this.cryptoService = cryptoService;
        this.userService = userService;
    }

    @GetMapping("/crypto/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getCryptoPrice(@PathVariable("token") String token) {
        return getPrice(cryptoHandlerUrl, token);
    }

    @PostMapping("/crypto/post")
    public ResponseEntity<ResponseTokenDTO> saveCrypto(@RequestBody CryptoCreateDto createDto/*, @AuthenticationPrincipal UserDetails user*/) {
        int status = 200;
        ResponseTokenDTO response;

        UserDetails user = new org.springframework.security.core.userdetails.User(
                "denkor",
                "null",
                new ArrayList<>()
        );
        try {
            User userEntity = userService.findByUserDetails(user).orElseThrow();
            cryptoService.create(createDto, userEntity);
            response = new ResponseTokenDTO(0, "OK");
        } catch (IllegalArgumentException e) {
            status = 404;
            response = new ResponseTokenDTO(0, e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/shares/ru/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getRuSharePrice(@PathVariable("token") String token) {
        return getPrice(ruShareHandlerUrl, token);
    }

    @GetMapping("/shares/en/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getEnSharePrice(@PathVariable("token") String token) {
        return getPrice(enShareHandlerUrl, token);
    }

    private ResponseEntity<ResponseTokenDTO> getPrice(String url, String token) {
        int status = 200;
        ResponseTokenDTO response = restTemplate.getForObject(url.replace("{token}", token), ResponseTokenDTO.class);
        if (response == null || response.getErrorMessage() == null || !response.getErrorMessage().isEmpty()) {
            status = 404;
        }
        return ResponseEntity.status(status).body(response);
    }
}
