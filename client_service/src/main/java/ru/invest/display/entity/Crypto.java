package ru.invest.display.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "crypto")
public class Crypto extends Product{
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String broker;
}
