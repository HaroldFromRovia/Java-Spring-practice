package ru.itis.kpfu.cloudlabwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Function;

@SpringBootApplication
@EnableAsync
public class CloudlabWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudlabWebAppApplication.class, args);
    }

}
