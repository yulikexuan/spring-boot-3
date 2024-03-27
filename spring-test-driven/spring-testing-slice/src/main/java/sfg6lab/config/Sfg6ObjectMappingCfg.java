//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Service.class, JsonComponent.class }),
        basePackages = { "sfg6lab.service", "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class Sfg6ObjectMappingCfg {

    @Bean
    public ObjectMapper sfg6ObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.indentOutput(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .build();
    }

}///:~
