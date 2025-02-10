//: sfg6lab.ai.service.domain.service.WeatherServiceFunction.java

package sfg6lab.ai.function.domain.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;
import sfg6lab.ai.function.domain.model.WeatherRequest;
import sfg6lab.ai.function.domain.model.WeatherResponse;

import java.net.URI;
import java.util.function.Function;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class WeatherServiceFunction
        implements Function<WeatherRequest, WeatherResponse> {

    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    private final String apiNinjasKey;

    @Override
    public WeatherResponse apply(@NonNull final WeatherRequest weatherRequest) {

        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();


        var response = restClient.get()
                .uri(uriBuilder ->
                        buildWeathreUri(weatherRequest, uriBuilder))
                .retrieve()
                .body(WeatherResponse.class);

        return response;
    }

    private static URI buildWeathreUri(
            @NonNull final WeatherRequest weatherRequest,
            @NonNull final UriBuilder uriBuilder) {

        System.out.println("Building URI for weather request with coordinates: "
                + "lat=" + weatherRequest.latitude() + ", lon=" + weatherRequest.longitude());

        log.debug(">>> Building URI for Weather Request with Coordinates: {}",
                weatherRequest);

        uriBuilder.queryParam("lat", weatherRequest.latitude());
        uriBuilder.queryParam("lon", weatherRequest.longitude());

        return uriBuilder.build();
    }

} /// :~