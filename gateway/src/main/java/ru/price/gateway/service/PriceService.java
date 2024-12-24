package ru.price.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.price.gateway.dto.TokenRequestDTO;
import ru.price.gateway.dto.TokenResponseDTO;
import ru.price.gateway.type.TokenType;

@Service
@Slf4j
public class PriceService {
    @Value("${ru.share.price.handler.url}")
    private String ruShareHandlerUrl;
    @Value("${en.share.price.handler.url}")
    private String enShareHandlerUrl;
    @Value("${crypto.price.handler.url}")
    private String cryptoHandlerUrl;

    @Value("${redis.ttl.sec}")
    private int redisTtl;
    private final RestTemplate restTemplate;
    private final RedisService redisService;

    public PriceService(RestTemplate restTemplate, RedisService redisService) {
        this.restTemplate = restTemplate;
        this.redisService = redisService;
    }

    public TokenResponseDTO getPrice(TokenRequestDTO token) {
        log.info("rq token: " + token);

        String msg = validateRequest(token);
        TokenResponseDTO tokenRs;

        if (msg == null) {
            tokenRs = getTokenFromCache(token);
            if(tokenRs == null) {
                try {
                    if (token.getType().equalsIgnoreCase(TokenType.CRYPTO.name())) {
                        tokenRs = getCryptoPrice(token.getToken());
                    } else if (token.getType().equalsIgnoreCase(TokenType.EN_SHARE.name())) {
                        tokenRs = getEnSharePrice(token.getToken());
                    } else if (token.getType().equalsIgnoreCase(TokenType.RU_SHARE.name())) {
                        tokenRs = getRuSharePrice(token.getToken());
                    }
                    if (tokenRs == null) {
                        tokenRs = new TokenResponseDTO(0, "Token type is not recognised");
                    }else {
                        redisService.save(token.getToken(), tokenRs, redisTtl);
                        log.info("save tokenRq: " + token.getToken() + ", tokenRs:" + tokenRs);
                    }
                } catch (ResourceAccessException e) {
                    tokenRs = new TokenResponseDTO(0, "Price service is shut down");
                }
            }
        } else {
            tokenRs =  new TokenResponseDTO(0, msg);
        }

        return tokenRs;
    }

    private TokenResponseDTO getTokenFromCache(TokenRequestDTO tokenRq) {
        Object cacheToken = redisService.get(tokenRq.getToken());
        log.info("cacheToken: "+ cacheToken);
        return (cacheToken == null) ? null : (TokenResponseDTO) cacheToken;
    }

    public TokenResponseDTO getCryptoPrice(String token) {
        return restTemplate.getForObject(cryptoHandlerUrl.replace("{token}", token), TokenResponseDTO.class);
    }

    public TokenResponseDTO getRuSharePrice(String token) {
        return restTemplate.getForObject(ruShareHandlerUrl.replace("{token}", token), TokenResponseDTO.class);
    }

    public TokenResponseDTO getEnSharePrice(String token) {
        return restTemplate.getForObject(enShareHandlerUrl.replace("{token}", token), TokenResponseDTO.class);
    }

    private String validateRequest(TokenRequestDTO token) {
        if (token == null) {
            return "Request is not provided";
        }

        if (token.getToken() == null || token.getToken().isEmpty()) {
            return "Token is not provided";
        }

        if (token.getType() == null || token.getType().isEmpty()) {
            return "Token type is not provided";
        }

        return null;
    }
}
