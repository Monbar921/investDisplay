package ru.invest.display;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.invest.display.entity.Country;
import ru.invest.display.entity.Share;
import ru.invest.display.util.HibernateUtil;

@SpringBootTest
public class AppTest {
    private final HibernateUtil hibernateUtil;

    @Autowired
    public AppTest(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @AfterAll
    public void closeSessionFactory(){
        hibernateUtil.closeSessionFactory();
    }

    @Test
    void testInsertShare(){
        try(Session session = hibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Share share = new Share("Apple", 100, 1, "AAPL", Country.EN);

            session.persist(share);

            session.getTransaction().commit();
        }
    }
}
