//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@Slf4j
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Controller.class,
                Service.class }),
        basePackages = {
                "sfg6lab.config",
                "sfg6lab.service",
                "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class Sfg6AppCfg {

    @Bean
    BCryptPasswordEncoder cryptEncoder() {
        return new BCryptPasswordEncoder();
    }

}///:~
