package ru.itis.kpfu.cloudlabbootfunctions.service.implementations;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.kpfu.cloudlabbootfunctions.models.Location;
import ru.itis.kpfu.cloudlabbootfunctions.models.SuggestionWrapper;
import ru.itis.kpfu.cloudlabbootfunctions.service.interfaces.LocationApiService;

import java.util.Collections;

@Service
public class DadataLocationServiceImpl implements LocationApiService {

    @Override
    public SuggestionWrapper getResponse(Location location) {

        HttpHeaders headers = setHeaders();
        HttpEntity<SuggestionWrapper> entityReq = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address?lat="
                + location.getLat() + "&lon=" + location.getLon();

        System.out.println(location.toString());

        if (location.getRadius_meters() != null) {
            url = url + "&radius_meters=" + location.getRadius_meters();
        }

        return restTemplate.exchange(url,
                HttpMethod.GET, entityReq, SuggestionWrapper.class).getBody();
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //TODO Подружить с Spring и использовать Environment
        headers.set("Authorization", System.getenv("DADATA_API_KEY"));
        return headers;
    }

}
