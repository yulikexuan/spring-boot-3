//: app.config.MainConfig.java


package app.config;


import app.aspect.ToLoggingAnnotationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"app.service", "app.aspect"})
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