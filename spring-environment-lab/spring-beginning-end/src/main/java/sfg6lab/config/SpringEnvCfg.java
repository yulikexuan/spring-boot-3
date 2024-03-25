//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class, Service.class}),
        basePackages = { "sfg6lab.service", "sfg6lab.config", "sfg6lab.app"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class SpringEnvCfg {

}///:~