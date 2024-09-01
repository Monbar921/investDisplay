package ru.invest.display.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import ru.invest.display.dao.BaseRepository;
import ru.invest.display.dao.Repository;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.BaseEntity;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;

import java.io.Serializable;
import java.util.Optional;

@Setter
@Getter
public abstract class ProductService <K extends Serializable, T extends Product<K>> {
    private UserService userService;
    private BaseRepository<K, T> repository;

    @Transactional
    public Long create(T entity) {
        Long shareId = null;
        Optional<User> opUser = userService.findByUsername(entity.getUser().getUsername());

        if (opUser.isPresent()){
            entity.getUser().setId(opUser.get().getId());
            opUser = userService.merge(entity.getUser());

            if (opUser.isPresent()){
                entity.setUser(opUser.get());

                shareId = (Long) repository.save(entity).getId();
            }
        }

        return shareId;
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
}
