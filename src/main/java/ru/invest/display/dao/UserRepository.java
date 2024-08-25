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
import ru.invest.display.entity.User;

import java.util.Optional;
@Repository
@Slf4j
public class UserRepository extends BaseRepository<Long, User> {
    public UserRepository(@Autowired EntityManager entityManager) {
        super(User.class, entityManager);
    }

    public Optional<User> findByUsername(String username) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("username"), username);

        cq.select(root).where(predicates);
        TypedQuery<User> query = getEntityManager().createQuery(cq);

        return query.getResultStream().findFirst();

    }
}
