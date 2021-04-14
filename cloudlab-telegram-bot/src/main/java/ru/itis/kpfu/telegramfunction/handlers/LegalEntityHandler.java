package ru.itis.kpfu.telegramfunction.handlers;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itis.kpfu.telegramfunction.models.Request;
import ru.itis.kpfu.telegramfunction.models.Response;
import ru.itis.kpfu.telegramfunction.models.Suggestion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LegalEntityHandler extends AzureSpringBootRequestHandler<Update, Suggestion> {

    @FunctionName("legal")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Update>> request,
            ExecutionContext context) {

        Update requestBody = request.getBody().orElseThrow(IllegalArgumentException::new);

        context.getLogger().info("Requesting legal entity with following parameters:  " + requestBody.toString());
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(requestBody, context))
                .header("Content-Type", "application/json")
                .build();
    }
}


