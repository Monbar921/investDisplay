package ru.invest.display.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.invest.display.entity.User;
import ru.invest.display.service.UserService;

@Aspect
@Component
public class UserCheckAspect {
    private final UserService userService;

    public UserCheckAspect(UserService userService) {
        this.userService = userService;
    }

    // Define a pointcut to match the methods you want to intercept
    @Pointcut("execution(* ru.invest.display.service.BankAccountService.*(..))") // Replace with your package
    public void methodsToIntercept() {
    }

    @Before("methodsToIntercept()")
    public void checkUserBeforeMethodExecution(JoinPoint joinPoint) {
        // Extract the method arguments
        Object[] args = joinPoint.getArgs();

        // Find the User parameter
        for (Object arg : args) {
            if (arg instanceof User user) {

                // Validate the user
                if (userService.isUserAuthorized(user)) {
                    throw new SecurityException("User is not authorized");
                }

                // Break after the User is found and validated
                break;
            } else if (arg instanceof UserDetails user) {

                // Validate the user
                if (userService.isUserAuthorized(user)) {
                    throw new SecurityException("User is not authorized");
                }

                // Break after the User is found and validated
                break;
            }
        }
    }
}
