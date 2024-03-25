//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({ Service.class }),
        basePackages = { "sfg6lab.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class SpringEnvCfg {

}///:~
