package ru.invest.display.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "share")
public class Share extends Product{
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String broker;
}
