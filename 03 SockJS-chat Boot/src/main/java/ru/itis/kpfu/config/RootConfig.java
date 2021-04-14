package ru.itis.kpfu.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@ComponentScan("ru.itis.kpfu")
@EnableWebSocket
@Import({WebSocketConfig.class, DataSourceConfig.class})
public class RootConfig extends WebMvcConfigurationSupport {


    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        return mapper;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();

        resolver.setCache(true);
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");

        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates");
        return freeMarkerConfigurer;
    }
}
