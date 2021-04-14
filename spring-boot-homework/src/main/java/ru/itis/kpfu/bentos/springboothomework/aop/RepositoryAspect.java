package ru.itis.kpfu.bentos.springboothomework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.springboothomework.dto.LogDto;
import ru.itis.kpfu.bentos.springboothomework.models.User;
import ru.itis.kpfu.bentos.springboothomework.utils.OutputHelper;

import java.util.Date;

@Aspect
@Component
public class RepositoryAspect {

    @Before("within(ru.itis.kpfu.bentos.springboothomework.repository..*+)")
    public void logBefore(JoinPoint joinPoint) {
        Date date = new Date();

        System.out.print(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.INFO + " Trying to");
        switchJoinPoint(joinPoint);
    }

    @After("within(ru.itis.kpfu.bentos.springboothomework.repository..*+)")
    public void logAfter(JoinPoint joinPoint) {
        Date date = new Date();

        System.out.print(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.INFO + " Trying to ");
        System.out.println(joinPoint.getSignature().getName()
                + " method execution successfully finished");
    }

    private void switchJoinPoint(JoinPoint joinPoint) {
        switch (joinPoint.getSignature().getName()) {
            case "save":
                System.out.println(" save user with "
                        + LogDto.from(((User) joinPoint.getArgs()[0])).toString());
                break;
            case "findById":
                System.out.println(" find user with id = "
                        + joinPoint.getArgs()[0]);
                break;
            case "findAll":
                System.out.println(" get all users from database");
                break;
            case "deleteById":
                System.out.println(" delete user with id = "
                        + joinPoint.getArgs()[0]);
                break;
        }
    }
}
