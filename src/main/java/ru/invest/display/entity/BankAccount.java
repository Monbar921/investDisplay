package ru.invest.display.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "bank_account")
public class BankAccount extends Product<Long>{
    @Column(nullable = false)
    private double interest;
    private LocalDate endDate;
    @Column(nullable = false)
    private String bank;
}
