package ru.invest.display.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Refill extends BaseEntity<Long>{
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @ManyToOne
    private Product<Long> product;
}
