package ru.invest.display.config;
//import org.hibernate.SessionFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.interceptor.TransactionInterceptor;

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

//    @PostConstruct
//    public void beginSession(){
//        getProxySession().beginTransaction();
//    }
    private Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Share.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);

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
