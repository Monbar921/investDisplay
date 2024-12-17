package ru.invest.display.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEstateDTO <T>{
    private String errorMessage;
    private List<T> data;
}
