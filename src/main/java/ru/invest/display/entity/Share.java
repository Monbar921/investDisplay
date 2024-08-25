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
@DiscriminatorValue(value = "share")
public class Share extends Product<Long>{
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String sector;
}
