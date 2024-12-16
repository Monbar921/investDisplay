package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import ru.invest.display.entity.BaseEntity;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
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
    public Optional<E> merge(E entity) {
        return Optional.ofNullable(entityManager.merge(entity));
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

    public Optional<E> findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(clazz);
        Root<E> root = cq.from(clazz);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), name);

        cq.select(root).where(predicates);
        TypedQuery<E> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();
    }


    public List<E> findByArguments(String name, Map<String, Object> arguments) {
        List<E> result;

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(clazz);
        Root<E> root = cq.from(clazz);

        if(arguments == null || arguments.size() == 0){
            result = new ArrayList<>();
        } else{
            Predicate[] predicates = new Predicate[arguments.size()];

            int i = 0;
            for (Map.Entry<String, Object> entry : arguments.entrySet()) {
                predicates[i++] = cb.equal(root.get(entry.getKey()), entry.getValue());
            }

            cq.select(root).where(predicates);

            TypedQuery<E> query = getEntityManager().createQuery(cq);
            result = query.getResultList();
        }

        return result;
    }

}