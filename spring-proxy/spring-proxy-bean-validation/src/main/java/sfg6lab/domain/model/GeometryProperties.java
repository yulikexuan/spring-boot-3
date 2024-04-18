//: sfg6lab.domain.model.GeometryProperties.java


package sfg6lab.domain.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;
import sfg6lab.config.YamlPropertySourceFactory;


/*
 * To use external resource files, MUST USE ANNOTATION @EnableConfigurationProperties
 *
 * When Spring Boot has read the Environment data and transferred it to the bean,
 * validation follows
 *
 * If there is an error, the container does not start and reports the problem
 */
@Validated
@EnableConfigurationProperties
@ConfigurationProperties("sfg6.geometry")
@PropertySource(
        value = {"classpath:geometry.yml"},
        factory = YamlPropertySourceFactory.class)
public record GeometryProperties(
        @NotNull @Valid Circle circle,
        @NotNull @Valid Rectangle rectangle) {


    public static record Circle(@Min(50) @Max(1000)int radius) {
    }

    public static record Rectangle(
            @Min(50) @Max(1000) int width,
            @Min(50) @Max(700) int height) {
    }

}///:~