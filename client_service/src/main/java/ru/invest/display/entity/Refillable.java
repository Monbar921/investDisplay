package ru.invest.display.entity;

import java.time.LocalDate;

public interface Refillable {
    boolean addRefill(double amount, LocalDate date);
}
