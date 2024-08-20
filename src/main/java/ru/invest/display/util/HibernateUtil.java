package ru.invest.display.util;
//import org.hibernate.SessionFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import ru.invest.display.entity.Product;
import ru.invest.display.entity.Share;

@org.springframework.context.annotation.Configuration
@Getter
public class HibernateUtil {
    private final SessionFactory sessionFactory;

    public HibernateUtil(){
        sessionFactory = buildSessionFactory();
    }

    private Configuration buildConfiguration(){
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Share.class);
        configuration.addAnnotatedClass(Product.class);

        return configuration;
    }

    private SessionFactory buildSessionFactory(){
        Configuration configuration = buildConfiguration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    public void closeSessionFactory(){
        sessionFactory.close();
    }
}
