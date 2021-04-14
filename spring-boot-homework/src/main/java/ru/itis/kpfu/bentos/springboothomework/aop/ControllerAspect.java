package ru.itis.kpfu.bentos.springboothomework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.kpfu.bentos.springboothomework.controllers.IndexController;
import ru.itis.kpfu.bentos.springboothomework.controllers.SignUpController;
import ru.itis.kpfu.bentos.springboothomework.utils.OutputHelper;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class ControllerAspect {

    @Before("within(ru.itis.kpfu.bentos.springboothomework.controllers..*+)")
    public void logBefore(JoinPoint joinPoint) {
        Date date = new Date();

        try {
            String uri = MvcUriComponentsBuilder
                    .fromMethodName(IndexController.class, joinPoint.getSignature().getName(), new Object[0]).build().toString();

            System.out.println(OutputHelper.DATE_FORMAT.format(date) + OutputHelper.INFO +
                    " An attempt to " + joinPoint.getSignature().getName() + " in class "
                    + joinPoint.getTarget().getClass() + " was started. Calling " + uri);

        } catch (IllegalArgumentException e) {
            System.out.println("Controller aspect has failed");
        }
    }


}
