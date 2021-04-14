package ru.itis.kpfu.telegramfunction.service.implementations;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.kpfu.telegramfunction.models.Request;
import ru.itis.kpfu.telegramfunction.models.SuggestionWrapper;
import ru.itis.kpfu.telegramfunction.service.interfaces.LegalApiService;

import java.util.Collections;

@Service
@AllArgsConstructor
public class DadataLegalServiceImpl implements LegalApiService {

    private final Environment environment;
    private final String API_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party";

    @Override
    public SuggestionWrapper getResponse(Request request) {
        HttpHeaders headers = setHeaders();
        HttpEntity<Request> entityReq = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(API_URL, HttpMethod.POST, entityReq, SuggestionWrapper.class).getBody();
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", environment.getProperty("dadata.api.key"));
        return headers;
    }
}
