//: sfg6lab.ai.function.config.OpenAiFuncCfg.java

package sfg6lab.ai.function.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sfg6lab.ai.function.domain.service.WeatherServiceFunction;


@Configuration
class OpenAiFuncCfg {

    @Value("${sfg.aiapp.apiNinjasKey}")
    private String apiNinjasKey;

    @Bean
    WeatherServiceFunction weatherFunc() {
        return WeatherServiceFunction.of(apiNinjasKey);
    }

} /// :~