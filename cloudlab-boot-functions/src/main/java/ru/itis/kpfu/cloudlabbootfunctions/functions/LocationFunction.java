package ru.itis.kpfu.cloudlabbootfunctions.functions;

import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.cloudlabbootfunctions.models.Location;
import ru.itis.kpfu.cloudlabbootfunctions.models.Suggestion;
import ru.itis.kpfu.cloudlabbootfunctions.service.interfaces.LocationApiService;
import ru.itis.kpfu.cloudlabbootfunctions.service.interfaces.TemplateService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class LocationFunction {

    private final TemplateService templateService;
    private final LocationApiService locationApiService;

    @Bean
    public Function<Location, String> location() {
        return (location) -> {

            List<Suggestion> response = locationApiService.getResponse(location).getSuggestions();
            Map<String, List<Suggestion>> map = new HashMap<>();
            map.put("addresses", response);

            try {
                templateService.setTemplateInput("location.ftl");

                return templateService.fillTemplate(map);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}
