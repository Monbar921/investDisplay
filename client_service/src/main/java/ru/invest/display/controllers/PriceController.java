package ru.invest.display.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.invest.display.dto.ResponseTokenDTO;

@RestController
@RequestMapping("/api")
public class PriceController {
    @Value("${ru.share.price.handler.url}")
    private String ruShareHandlerUrl;
    @Value("${en.share.price.handler.url}")
    private String enShareHandlerUrl;
    @Value("${crypto.price.handler.url}")
    private String cryptoHandlerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/crypto/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getCryptoPrice(@PathVariable("token") String token) {
        int status = 200;
        ResponseTokenDTO response = restTemplate.getForObject(cryptoHandlerUrl.replace("{token}", token), ResponseTokenDTO.class);
        if (response == null || response.getErrorMessage() == null || !response.getErrorMessage().isEmpty()) {
            status = 404;
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/shares/ru/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getRuSharePrice(@PathVariable("token") String token) {
        int status = 200;
        ResponseTokenDTO response = restTemplate.getForObject(ruShareHandlerUrl.replace("{token}", token), ResponseTokenDTO.class);
        if (response == null || response.getErrorMessage() == null || !response.getErrorMessage().isEmpty()) {
            status = 404;
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/shares/en/price/{token}")
    public ResponseEntity<ResponseTokenDTO> getEnSharePrice(@PathVariable("token") String token) {
        int status = 200;
        ResponseTokenDTO response = restTemplate.getForObject(enShareHandlerUrl.replace("{token}", token), ResponseTokenDTO.class);
        if (response == null || response.getErrorMessage() == null || !response.getErrorMessage().isEmpty()) {
            status = 404;
        }
        return ResponseEntity.status(status).body(response);
    }
}
