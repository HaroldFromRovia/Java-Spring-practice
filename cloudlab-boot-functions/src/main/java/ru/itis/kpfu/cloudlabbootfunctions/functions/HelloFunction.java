package ru.itis.kpfu.cloudlabbootfunctions.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.cloudlabbootfunctions.models.greeting.Greeting;
import ru.itis.kpfu.cloudlabbootfunctions.models.greeting.User;

import java.util.function.Function;

@Component
public class HelloFunction {

    @Bean
    public Function<User, Greeting> hello() {
        return user -> new Greeting("Welcome, " + user.getName());
    }

}
