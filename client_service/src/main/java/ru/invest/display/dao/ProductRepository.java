package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.invest.display.entity.BaseEntity;

import java.io.Serializable;
import java.util.Optional;

public abstract class ProductRepository<K extends Serializable, E extends BaseEntity<K>>  extends BaseRepository<K, E> {
    public ProductRepository(Class<E> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
    }

    public Optional<E> findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(super.getClazz());
        Root<E> root = cq.from(super.getClazz());

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), name);

        cq.select(root).where(predicates);
        TypedQuery<E> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();
    }
}
