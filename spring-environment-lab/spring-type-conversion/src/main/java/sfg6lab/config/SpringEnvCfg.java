//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sfg6lab.domain.service.StringToPathConverter;

import java.nio.file.Path;
import java.util.UUID;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({Component.class, Service.class}),
        basePackages = { "sfg6lab.service",
                "sfg6lab.config",
                "sfg6lab.app",
                "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class SpringEnvCfg {

    @Value("${sfg6.profile.id}")
    private UUID id;

    @Value("${sfg6.profile.username}")
    private String username;

    @Value("${sfg6.profile.secret-level}")
    int secretLevel;

    @Bean
    UUID profileId() {
        return id;
    }

    @Bean
    String profileUsername() {
        return username;
    }

    @Bean
    Integer profileSecretLevel() {
        return secretLevel;
    }

    @Bean
    Converter<String, Path> stringToPathConverter() {
        return new StringToPathConverter();
    }

    /*
     * spring-shell-starter and spring-web-starter have managed bean of
     * ApplicationConversionService by default
     */
    @Bean
    @ConditionalOnMissingBean
    ApplicationConversionService conversionService(
            Converter<String, Path> stringToPathConverter) {

        // This is the best way, which is using ApplicationConversionService
        var appConversionService = new ApplicationConversionService();
        // or, use:
        //   appConversionService = ApplicationConversionService.getSharedInstance();

        appConversionService.addConverter(stringToPathConverter);

        return appConversionService;

//        This is also a good way to go
//        return new DefaultConversionService();

//        This is not a good one
//        return DefaultConversionService.getSharedInstance();
    }

}///:~