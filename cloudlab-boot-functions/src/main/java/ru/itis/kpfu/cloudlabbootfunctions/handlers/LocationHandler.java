package ru.itis.kpfu.cloudlabbootfunctions.handlers;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.MethodNotAllowedException;
import ru.itis.kpfu.cloudlabbootfunctions.models.Location;

import java.util.*;

@Component
public class LocationHandler extends AzureSpringBootRequestHandler<Location, String> {

    @FunctionName("location")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST, HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Location>> request,
            ExecutionContext context) {

        Location location;

        //TODO Тяжело читать код
        if (request.getHttpMethod().compareTo(HttpMethod.POST) == 0) {

            location = request.getBody().orElseThrow(IllegalArgumentException::new);

        } else if (request.getHttpMethod().compareTo(HttpMethod.GET) == 0) {

            Map<String, String> params = request.getQueryParameters();
            location = parseMap(params);

        } else {
            return request.createResponseBuilder(HttpStatus.I_AM_A_TEAPOT)
                    .header("Content-Type", "text/html")
                    .build();
        }


        context.getLogger().info("Requesting location with following parameters:  " + location.toString());
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(location, context))
                .header("Content-Type", "text/html")
                .build();
    }

    private Location parseMap(Map<String, String> params) {
        Location location;
        if (params.containsKey("radius_meters")) {

            location = Location.builder()
                    .lon(Double.parseDouble(params.get("lon")))
                    .lat(Double.parseDouble(params.get("lat")))
                    .radius_meters(Double.parseDouble(params.get("radius_meters")))
                    .build();

        } else location = Location.builder()
                .lon(Double.parseDouble(params.get("lon")))
                .lat(Double.parseDouble(params.get("lat")))
                .build();
        return location;
    }
}
