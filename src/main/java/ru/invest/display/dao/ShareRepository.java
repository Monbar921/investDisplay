package ru.invest.display.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.invest.display.entity.Share;

@Repository
public class ShareRepository extends BaseRepository<Long, Share> {
    public ShareRepository(@Autowired EntityManager entityManager) {
        super(Share.class, entityManager);
    }
}
