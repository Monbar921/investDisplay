package ru.invest.display.interceptor;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;

@Component
public class TransactionInterceptor {

    private final SessionFactory sessionFactory;

    public TransactionInterceptor(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RuntimeType
    public Object intercept(@SuperCall Callable<Object> call, @Origin Method method) throws Exception {
        Transaction transaction = null;
        boolean transactionStarted = false;
        if (method.isAnnotationPresent(Transactional.class)) {
            System.out.println("found Transactional");
            transaction = sessionFactory.getCurrentSession().getTransaction();

            System.out.println("tr transaction "+ transaction);
            System.out.println("tr alive "+ transaction.isActive());
            if (!transaction.isActive()) {
                System.out.println("Transactional alive");
                transaction.begin();
                transactionStarted = true;
            }
        }

        Object result;
        try {
            result = call.call();
            if (transactionStarted) {
                transaction.commit();
            }
        } catch (Exception exception) {
            if (transactionStarted) {
                transaction.rollback();
            }
            throw exception;
        }

        return result;
    }
}