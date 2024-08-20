package ru.invest.display.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "share")
public class Share extends Product<Long>{
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    public Share(String name, double price, double quantity, String code, Country country){
        super.setName(name);
        super.setPrice(price);
        super.setQuantity(quantity);

        this.code = code;
        this.country = country;
    }
}
