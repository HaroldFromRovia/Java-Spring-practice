package ru.itis.kpfu.cloudlabfunctions;

import com.microsoft.azure.functions.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.itis.kpfu.cloudlabfunctions.models.SuggestionWrapper;

import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        HttpHeaders headers = setHeaders();
        HttpEntity<SuggestionWrapper> entityReq = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        SuggestionWrapper response = restTemplate.exchange("https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address?lat=55.878&lon=37.653",
                org.springframework.http.HttpMethod.GET, entityReq, SuggestionWrapper.class).getBody();

        System.out.println(response.getSuggestions().get(0).getValue());
    }

    private static HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //TODO Подружить с Spring и использовать Environment
        headers.set("Authorization", "Token 5c6c9102dbd5e980d07c9afe08e4cf137f94ecf8");
        return headers;
    }
}

