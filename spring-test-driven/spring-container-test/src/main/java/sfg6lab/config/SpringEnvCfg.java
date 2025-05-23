//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sfg6lab.domain.model.FileSystem;
import sfg6lab.domain.model.Profile;
import sfg6lab.domain.service.LocalKeycloakService;
import sfg6lab.domain.service.MvnLocalRepositoryService;

import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({ Service.class, Controller.class }),
        basePackages = { "sfg6lab.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class SpringEnvCfg {

    @Bean
    Profile profile(ProfileConfigProperties profileConfigProperties) {
        return profileConfigProperties.profile();
    }

    @Bean
    Path mvnHome() {
        return Paths.get(System.getProperty("user.home"), ".m2/repository");
    }

    @Bean
    MvnLocalRepositoryService mvnLocalRepositoryService(Path mvnHome) {
        return MvnLocalRepositoryService.of(mvnHome);
    }

    @Bean
    @ConditionalOnClass(LocalKeycloakService.class)
    // @Conditional(TestingCondition.class)
    // @ConditionalOnProperty(
    //        name = "spirng.profiles.active",
    //        havingValue = "test",
    //        matchIfMissing = false)
    LocalKeycloakService localKeycloakService() {
        return LocalKeycloakService.localKeycloakService();
    }

}///:~