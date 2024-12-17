package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.User;

import java.util.Optional;

@Repository
public class BankAccountRepository extends ProductRepository<Long, BankAccount> {
    public BankAccountRepository(@Autowired EntityManager entityManager) {
        super(BankAccount.class, entityManager);
    }

//    public Optional<BankAccount> findByBankAndNameAndUserId(String bank, String name, Integer userId) {
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<BankAccount> cq = cb.createQuery(this.getClazz());
//        Root<BankAccount> root = cq.from(this.getClazz());
//
//        Join<Product, User> userJoin = productRoot.join("user");
//
//        Predicate equalNamePred = cb.equal(root.get("name"), name);
//        Predicate equalBankPred = cb.equal(root.get("bank"), bank);
//        Predicate equalBankPred = cb.equal(root.get("user_id"), bank);
//
//        cq.select(root).where(cb.and(equalBankPred, equalNamePred));
//
//        TypedQuery<BankAccount> query = getEntityManager().createQuery(cq);
//
//        return query.getResultStream().findFirst();
//    }

    public Optional<BankAccount> findByBankAndNameAndUserId(String bank, String name, Long userId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BankAccount> cq = cb.createQuery(this.getClazz());
        Root<BankAccount> root = cq.from(this.getClazz());

        Join<BankAccount, Product<Long>> productJoin = root.join("id");

        Predicate equalNamePred = cb.equal(productJoin.get("name"), name);
        Predicate equalBankPred = cb.equal(root.get("bank"), bank);
        Predicate userPred = cb.equal(productJoin.get("user_id"), userId);

        cq.select(root).where(cb.and(equalBankPred, equalNamePred, userPred));

        TypedQuery<BankAccount> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();
    }
}
