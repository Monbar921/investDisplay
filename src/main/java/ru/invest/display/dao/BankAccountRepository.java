package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.Share;

import java.util.Optional;

@Repository
public class BankAccountRepository extends BaseRepository<Long, BankAccount> {
    public BankAccountRepository(@Autowired EntityManager entityManager) {
        super(BankAccount.class, entityManager);
    }

    public Optional<BankAccount> findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BankAccount> cq = cb.createQuery(BankAccount.class);
        Root<BankAccount> root = cq.from(BankAccount.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), name);

        cq.select(root).where(predicates);
        TypedQuery<BankAccount> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();
    }
}
