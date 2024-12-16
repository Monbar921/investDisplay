package ru.invest.display.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Product<T extends Serializable> extends BaseEntity<T> implements Refillable{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double buyPrice;
    @Column(nullable = false)
    private double currentPrice;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private LocalDate startDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "refill_id")
    private List<Refill<T>> refills;

    @Override
    public boolean addRefill(double amount, LocalDate date) {
        refills.add(new Refill<T>(amount, date, this));

        return true;
    }
}
