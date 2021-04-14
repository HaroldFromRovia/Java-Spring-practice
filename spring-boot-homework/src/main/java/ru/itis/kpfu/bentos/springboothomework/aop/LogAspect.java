package ru.itis.kpfu.bentos.springboothomework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.springboothomework.utils.OutputHelper;

import java.security.Signature;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Before("@annotation(ru.itis.kpfu.bentos.springboothomework.aop.annotations.LogMethod)")
    public void logBefore(JoinPoint joinPoint) {
        Date date = new Date();

        System.out.println(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.INFO +
                " An attempt to " + joinPoint.getSignature().getName() + " in class "
                + joinPoint.getTarget().getClass() + " was started.");

    }

    @After("@annotation(ru.itis.kpfu.bentos.springboothomework.aop.annotations.LogMethod)")
    public void logAfter(JoinPoint joinPoint) {
        Date date = new Date();

        System.out.println(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.INFO +
                " Execution of the " + joinPoint.getSignature().getName() + " in class "
                + joinPoint.getTarget().getClass() + " method is complete.");
    }
}
