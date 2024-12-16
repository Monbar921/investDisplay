package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.Realty;

@Repository
public class RealtyRepository extends ProductRepository<Long, Realty> {
    public RealtyRepository(@Autowired EntityManager entityManager) {
        super(Realty.class, entityManager);
    }
}
