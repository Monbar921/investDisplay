package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.invest.display.dao.BaseRepository;
import ru.invest.display.entity.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Setter
@Getter
@Service
public abstract class ProductService<T extends Product> {
    private UserService userService;
    private BaseRepository<Long, T> repository;

    @Transactional
    public Long create(T entity, User user) {
        userService.validateUser(user);
        User managedUser = userService.merge(user).orElseThrow();
        entity.setUser(managedUser);

        return (Long) repository.save(entity).getId();
    }

    @Transactional
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean delete(Long id) {
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
