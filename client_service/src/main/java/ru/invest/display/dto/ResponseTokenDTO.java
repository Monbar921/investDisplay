package ru.invest.display.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTokenDTO {
    @JsonProperty("current_price")
    private  double price;
    @JsonProperty("error_msg")
    private  String errorMessage;

    public ResponseTokenDTO() {
    }

    public ResponseTokenDTO(double price, String errorMessage) {
        this.price = price;
        this.errorMessage = errorMessage;
    }

    public ResponseTokenDTO(String price, String errorMessage) {
        double tempPrice = 0;
        if(!(price.isEmpty() || price.isBlank())){
            try {
                tempPrice = Double.parseDouble(price);
            }catch (NumberFormatException e){
                tempPrice = 0;
            }
        }

        this.price = tempPrice;
        this.errorMessage = errorMessage;
    }

    public double getPrice() {
        return price;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
