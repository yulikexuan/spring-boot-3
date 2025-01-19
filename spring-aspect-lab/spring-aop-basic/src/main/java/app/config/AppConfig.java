//: app.config.MainConfig.java


package app.config;


import app.aspect.ToLoggingAnnotationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        basePackages = {"app.service",
                "app.aspect",
                "app.controller"},
        includeFilters = @ComponentScan.Filter({
                RestController.class,
                Service.class,
                Component.class,
                ControllerAdvice.class}))
public class AppConfig {

//    @Bean
//    LoggingAspect loggingAspect() {
//        return LoggingAspect.of();
//    }

    @Bean
    ToLoggingAnnotationAspect toLoggingAnnotationAspect() {
        return ToLoggingAnnotationAspect.of();
    }

}///:~