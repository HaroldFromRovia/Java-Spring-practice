package ru.itis.kpfu.cloudlabfunctions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.itis.kpfu.cloudlabfunctions.models.SuggestionWrapper;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class LocationFunction {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("location")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        Map<String, String> query = request.getQueryParameters();

        HttpHeaders headers = setHeaders();
        HttpEntity<SuggestionWrapper> entityReq = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        SuggestionWrapper response = restTemplate.exchange("https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address?lat=55.878&lon=37.653",
                org.springframework.http.HttpMethod.GET, entityReq, SuggestionWrapper.class).getBody();

        return request.createResponseBuilder(HttpStatus.OK).body(response.getSuggestions().get(0)).build();
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //TODO Подружить с Spring и использовать Environment
        headers.set("Authorization", "Token 5c6c9102dbd5e980d07c9afe08e4cf137f94ecf8");
        return headers;
    }

}
