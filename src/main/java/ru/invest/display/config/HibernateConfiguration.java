package ru.invest.display.config;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import ru.invest.display.entity.*;

import java.lang.reflect.Proxy;

@org.springframework.context.annotation.Configuration
@Slf4j
public class HibernateConfiguration {
    @Getter
    private SessionFactory sessionFactory;

    @Bean(destroyMethod = "")
    public EntityManager entityManager() {
        return getProxySession();
    }

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Share.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(BankAccount.class);
        configuration.addAnnotatedClass(House.class);
        configuration.addAnnotatedClass(Crypto.class);

        return configuration;
    }

    private SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    private Session getProxySession() {
        return (Session) Proxy.newProxyInstance(HibernateConfiguration.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }
}
