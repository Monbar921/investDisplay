package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class BankAccountRepository extends ProductRepository<Long, BankAccount> {
    public BankAccountRepository(@Autowired EntityManager entityManager) {
        super(BankAccount.class, entityManager);
    }

    public List<BankAccount> findByBankAndNameAndUserId(String bank, String name, User user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BankAccount> cq = cb.createQuery(this.getClazz());
        Root<BankAccount> root = cq.from(this.getClazz());

        Predicate equalNamePred = cb.equal(root.get("name"), name);
        Predicate equalBankPred = cb.equal(root.get("bank"), bank);
        Predicate equalUserPred = cb.equal(root.get("user"), user);

        cq.select(root).where(cb.and(equalBankPred, equalNamePred, equalUserPred));

        TypedQuery<BankAccount> query = getEntityManager().createQuery(cq);

        return query.getResultStream().toList();
    }

}
