package ru.itis.kpfu.telegramfunction.functions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itis.kpfu.telegramfunction.models.Request;
import ru.itis.kpfu.telegramfunction.models.Response;
import ru.itis.kpfu.telegramfunction.models.Suggestion;
import ru.itis.kpfu.telegramfunction.service.interfaces.LegalApiService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class LegalEntityFunction {

    private final LegalApiService apiService;

    @Bean
    public Function<Update, Response> legal() {
        return (update) -> {

            String message = update.getMessage()
                    .getText();
            Long chatId = update.getMessage()
                    .getChat()
                    .getId();

            if (message.split(" ")[0].equals("/legal")) {

                List<Suggestion> suggestions = apiService.getResponse(Request.builder()
                        .query(message.split(" ")[1])
                        .build())
                        .getSuggestions();
                if (suggestions.size() == 1) {

                    return Response.builder()
                            .chat_id(chatId)
                            .text(suggestions.get(0).getValue())
                            .build();
                } else {
                    return Response.builder()
                            .chat_id(chatId)
                            .text("Specified data is not correct, or company not found in database")
                            .build();
                }
            } else {
                return Response.builder()
                        .chat_id(chatId)
                        .text("Command not found")
                        .build();
            }
        };
    }
}
