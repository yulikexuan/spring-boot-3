//: sfg6lab.ai.function.config.OpenAiFuncCfg.java

package sfg6lab.ai.function.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import sfg6lab.ai.function.domain.service.WeatherServiceFunction;


@Configuration
public class OpenAiFuncCfg {

    @Value("${sfg.aiapp.apiNinjasKey}")
    private String apiNinjasKey;

    @Bean
    public WeatherServiceFunction weatherFunc(RestClient.Builder restClientBuilder) {
        return WeatherServiceFunction.of(restClientBuilder);
    }

} /// :~