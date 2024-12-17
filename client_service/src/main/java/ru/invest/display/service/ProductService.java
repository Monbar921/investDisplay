package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import ru.invest.display.dao.BaseRepository;
import ru.invest.display.entity.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Setter
@Getter
public abstract class ProductService<K extends Serializable, T extends Product<K>> {
    private UserService userService;
    private BaseRepository<K, T> repository;

    @Transactional
    public Long create(T entity, User user) {
        userService.validateUser(user);
        User managedUser = userService.merge(user).orElseThrow();
        entity.setUser(managedUser);

        return (Long) repository.save(entity).getId();
    }

    @Transactional
    public Optional<T> findById(K id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean delete(K id) {
        var opEntity = repository.findById(id);
        opEntity.ifPresent(entity -> repository.delete(entity.getId()));
        return opEntity.isPresent();
    }

    @Transactional
    public boolean addRefill(double amount, LocalDate date, T product) {
//        refills.add(new Refill<T>(amount, date, this));

        return true;
    }
}
