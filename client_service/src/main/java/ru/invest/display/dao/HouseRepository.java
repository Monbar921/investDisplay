package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.House;
@Repository
public class HouseRepository  extends ProductRepository<Long, House> {
    public HouseRepository(@Autowired EntityManager entityManager) {
        super(House.class, entityManager);
    }
}
