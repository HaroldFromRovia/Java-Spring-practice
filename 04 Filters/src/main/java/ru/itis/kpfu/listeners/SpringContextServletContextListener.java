package ru.itis.kpfu.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.kpfu.config.ApplicationContextConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SpringContextServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("springContext", springContext);
        System.out.println("[INFO]: Spring context was initialized");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("[INFO]: Spring context was destroyed");
    }
}
