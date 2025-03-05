//: sfg6lab.ai.function.domain.service.WeatherServiceFunctionTest.java

package sfg6lab.ai.function.domain.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import sfg6lab.ai.function.config.OpenAiFuncCfg;
import sfg6lab.ai.function.domain.model.WeatherRequest;
import sfg6lab.ai.function.domain.model.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;


// @ExtendWith(MockitoExtension.class)
@Import(OpenAiFuncCfg.class)
@RestClientTest(WeatherServiceFunction.class)
@DisplayName("Test WeatherServiceFunction Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WeatherServiceFunctionTest {

    public static final String WEATHER_QUESTION_URL =
            "https://api.api-ninjas.com/v1/weather?lat=45.5088&lon=-73.5878";

    @Autowired
    private WeatherServiceFunction weatherFunc;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:/stubs/answer.weather.json")
    private Resource answer;
    private WeatherRequest request;

    @BeforeEach
    void setUp() {
        assertThat(answer).isNotNull();
        request = new WeatherRequest(45.5088, -73.5878);
    }

    @Test
    @SneakyThrows
    void available_To_Get_Weather_By_Latitude_Longitude() {

        // Given
        mockServer.expect(requestTo(endsWith("weather?lat=45.5088&lon=-73.5878")))
                .andExpect(method(GET))
                .andExpect(header("X-Api-Key", any(String.class)))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess(answer, MediaType.APPLICATION_JSON));

        // When
        WeatherResponse response = weatherFunc.apply(request);

        // Then
        assertThat(response.temp()).isEqualTo(1);

        mockServer.verify();
    }

    @Test
    void throws_Exception_If_Failed() {

        // Given
        mockServer.expect(requestTo(endsWith("weather?lat=45.5088&lon=-73.5878")))
                .andExpect(method(GET))
                .andExpect(header("X-Api-Key", any(String.class)))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withServerError());

        // When & Then
        assertThatThrownBy(() -> weatherFunc.apply(request))
                .isInstanceOf(HttpServerErrorException.class);
    }

    @Test
    void throws_Exception_If_Failed_With_Status_Codes() {

        // Given
        mockServer.expect(requestTo(endsWith("weather?lat=45.5088&lon=-73.5878")))
                .andExpect(method(GET))
                .andRespond(withStatus(HttpStatus.FORBIDDEN));

        // When & Then
        assertThatThrownBy(() -> weatherFunc.apply(request))
                .isInstanceOf(HttpClientErrorException.class);
    }
}
