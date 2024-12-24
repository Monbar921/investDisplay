package ru.price.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.price.gateway.dto.TokenRequestDTO;
import ru.price.gateway.dto.TokenResponseDTO;
import ru.price.gateway.service.PriceService;
import ru.price.gateway.type.TokenType;

@RestController
@RequestMapping("/api")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/crypto/price/{token}")
    public ResponseEntity<TokenResponseDTO> getCryptoPrice(@PathVariable("token") String token) {
        TokenResponseDTO response = priceService.getPrice(new TokenRequestDTO(TokenType.CRYPTO.name(), token));
        int status = getStatus(response);
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/shares/ru/price/{token}")
    public ResponseEntity<TokenResponseDTO> getRuSharePrice(@PathVariable("token") String token) {
        TokenResponseDTO response = priceService.getPrice(new TokenRequestDTO(TokenType.RU_SHARE.name(), token));
        int status = getStatus(response);
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/shares/en/price/{token}")
    public ResponseEntity<TokenResponseDTO> getEnSharePrice(@PathVariable("token") String token) {
        TokenResponseDTO response = priceService.getPrice(new TokenRequestDTO(TokenType.EN_SHARE.name(), token));
        int status = getStatus(response);
        return ResponseEntity.status(status).body(response);
    }

    private int getStatus(TokenResponseDTO dto){
        int status = 200;
        if (dto == null || (dto.getErrorMessage() != null && !dto.getErrorMessage().equalsIgnoreCase("OK"))) {
            status = 404;
        }

        return status;
    }
}
