package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.Refill;
import ru.invest.display.entity.User;

import java.sql.Ref;
import java.util.Optional;

@Repository
@Slf4j
public class RefillRepository{
//public class RefillRepository extends BaseRepository<Long, Refill> {
//    public RefillRepository(@Autowired EntityManager entityManager) {
//        super(Refill.class, entityManager);
//    }
//
//    public Optional<Refill> findByProductId(Long productId) {
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Refill> cq = cb.createQuery(Refill.class);
//        Root<Refill> root = cq.from(Refill.class);
//
//        Predicate[] predicates = new Predicate[1];
//        predicates[0] = cb.equal(root.get("product_id"), productId);
//
//        cq.select(root).where(predicates);
//        TypedQuery<Refill> query = getEntityManager().createQuery(cq);
//
//        return query.getResultStream().findFirst();
//    }
}
