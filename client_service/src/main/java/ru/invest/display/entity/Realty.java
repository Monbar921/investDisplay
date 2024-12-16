package ru.invest.display.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "house")
public class Realty extends Product<Long> {
    @Column(nullable = false)
    private boolean isCommercial;
    private double rent;

    @Override
    public boolean addRefill(double amount, LocalDate date) {
        if (isCommercial) {
            super.addRefill(amount, date);

            return true;
        } else {
            log.info("Trying to add refill to noncommercial house");
            return false;
        }
    }
}
