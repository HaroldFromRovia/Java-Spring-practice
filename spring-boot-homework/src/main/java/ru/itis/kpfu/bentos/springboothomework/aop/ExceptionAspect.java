package ru.itis.kpfu.bentos.springboothomework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.springboothomework.utils.OutputHelper;

import java.util.Date;

@Aspect
public class ExceptionAspect {

    @AfterThrowing(value = "within (ru.itis.kpfu.bentos.springboothomework..*)", throwing = "exception")
    public void log(JoinPoint joinPoint, Throwable exception) {
        Date date = new Date();
        System.out.println(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.ERROR + " LoggingAspect.logAfterThrowingAllMethods() " + exception);
    }

}
