//: springlab.config.ComponentExcludeFiltersConfig.java


package springlab.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springlab.domain.service.ConfigDemoInfo;
import springlab.service.FileSystemService;

import java.nio.file.Path;

import static org.springframework.context.annotation.ComponentScan.Filter;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import static org.springframework.context.annotation.FilterType.REGEX;


@Configuration
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {"springlab.service", "springlab.domain.service"},
        includeFilters = @Filter(type = REGEX, pattern = ".*(Controller|Service)"),
        excludeFilters = @Filter(type = ASSIGNABLE_TYPE, classes = ConfigDemoInfo.class )
)
public class ComponentExcludeFiltersConfig {

    @Value("${spring.lab.file.system.user.home.property.name}")
    private String systemUserHomePropertyName;

    @Bean
    public Path root() {
        return FileSystemService.root(systemUserHomePropertyName);
    }

}///:~