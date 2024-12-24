package ru.price.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDTO {
    @JsonProperty("current_price")
    private double price;
    @JsonProperty("error_msg")
    private String errorMessage;

    public TokenResponseDTO(String price, String errorMessage) {
        double tempPrice = 0;
        if (!(price.isEmpty() || price.isBlank())) {
            try {
                tempPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                tempPrice = 0;
            }
        }

        this.price = tempPrice;
        this.errorMessage = errorMessage;
    }
}
