package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.Crypto;

import java.util.Optional;

@Repository
public class CryptoRepository extends ProductRepository<Long, Crypto> {
    public CryptoRepository(@Autowired EntityManager entityManager) {
        super(Crypto.class, entityManager);
    }
    public Optional<Crypto> findByCode(String code) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Crypto> cq = cb.createQuery(Crypto.class);
        Root<Crypto> root = cq.from(Crypto.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("code"), code);

        cq.select(root).where(predicates);
        TypedQuery<Crypto> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();
    }
}
