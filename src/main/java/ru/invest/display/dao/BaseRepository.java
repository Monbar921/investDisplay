package ru.invest.display.dao;


import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import ru.invest.display.entity.BaseEntity;
import ru.invest.display.entity.User;

import java.io.Serializable;

@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        entityManager.remove(entityManager.find(clazz, id));
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }

    public List<E> findByArguments(String name, double price, double quantity, String platform) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(clazz);
        Root<E> root = cq.from(clazz);

        Predicate[] predicates = new Predicate[4];
        predicates[0] = cb.equal(root.get("name"), name);
        predicates[1] = cb.equal(root.get("price"), price);
        predicates[2] = cb.equal(root.get("quantity"), quantity);
        predicates[3] = cb.equal(root.get("platform"), platform);

        cq.select(root).where(predicates);
        TypedQuery<E> query = getEntityManager().createQuery(cq);


        return query.getResultList();
    }

}