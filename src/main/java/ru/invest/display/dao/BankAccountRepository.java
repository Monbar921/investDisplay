package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.BankAccount;

@Repository
public class BankAccountRepository extends ProductRepository<Long, BankAccount> {
    public BankAccountRepository(@Autowired EntityManager entityManager) {
        super(BankAccount.class, entityManager);
    }
}
