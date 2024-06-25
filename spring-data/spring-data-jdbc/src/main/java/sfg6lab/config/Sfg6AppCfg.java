//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
                Service.class,
                Repository.class}),
        basePackages = { "sfg6lab.service",
                "sfg6lab.domain.model",
                "sfg6lab.domain.service",
                "sfg6lab.repository"})
@EntityScan(basePackages = { "sfg6lab.domain.model" })
@EnableJdbcRepositories(basePackages = { "sfg6lab.repository" })
public class Sfg6AppCfg implements AsyncConfigurer {
}///:~
