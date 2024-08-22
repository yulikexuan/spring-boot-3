//: sfg6lab.config.Sfg6WebMvcCfg.java

package sfg6lab.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc
@Configuration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Controller.class,
                Service.class,
                Repository.class}),
        basePackages = {"sfg6lab.domain.model",
                "sfg6lab.domain.repository",
                "sfg6lab.domain.service",
                "sfg6lab.service",
                "sfg6lab.controller"})
@EntityScan(basePackages = { "sfg6lab.domain.model" })
@EnableAutoConfiguration
public class Sfg6WebMvcCfg implements WebMvcConfigurer {

} ///:~