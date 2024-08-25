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

    public Share(String name, double price, double quantity, String platform,
                 String code, String country, String sector){
        super.setName(name);
        super.setPrice(price);
        super.setQuantity(quantity);
        super.setPlatform(platform);

        this.code = code;
        this.country = country;
        this.sector = sector;
    }
}
