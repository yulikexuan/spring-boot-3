//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.apache.commons.lang3.SystemProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@EnableScheduling
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({ Service.class }),
        basePackages = { "sfg6lab.service", "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class Sfg6AppCfg {

    @Bean
    ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    @Bean
    List<String> javaInfo() {
        return List.of("Java Home: " + SystemProperties.getJavaHome(),
                "Java Runtime Name: " + SystemProperties.getJavaRuntimeName(),
                "Java Specification Name: " + SystemProperties.getJavaSpecificationName(),
                "Java Specification Vendor: " + SystemProperties.getJavaSpecificationVendor(),
                "Java Specification Version: " + SystemProperties.getJavaSpecificationVersion(),
                "Java Vendor: " + SystemProperties.getJavaVendor(),
                "Java Version: " + SystemProperties.getJavaVersion(),
                "User Home: " + SystemProperties.getUserHome(),
                "User Language: " + SystemProperties.getUserLanguage(),
                "User Name: " + SystemProperties.getUserName(),
                "User Timezone: " + SystemProperties.getUserTimezone(),
                "User Country: " + SystemProperties.getUserCountry());
    }

}///:~
