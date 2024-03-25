//: springlab.config.SpringLabConfig.java


package springlab.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import springlab.service.FileSystemService;

import java.nio.file.Path;


// @Configuration
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {"springlab.service"},
        includeFilters = @ComponentScan.Filter(Service.class)
)
public class ComponentIncludeFiltersConfig {

    @Value("${spring.lab.file.system.user.home.property.name}")
    private String systemUserHomePropertyName;

    @Bean
    public Path root() {
        return FileSystemService.root(systemUserHomePropertyName);
    }

}///:~
