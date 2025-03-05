//: sfg6lab.config.DevEnvLoggingConfiguration.java

package sfg6lab.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sfg6lab.domain.model.FileSystem;
import sfg6lab.domain.service.LocalKeycloakService;


@Configuration
// @Conditional(TestingCondition.class)
//@ConditionalOnProperty(
//        name = "spirng.profiles.active",
//        havingValue = "test",
//        matchIfMissing = false)
@ConditionalOnClass(FileSystem.class)
class TestingConfiguration {

    @Bean
    LocalKeycloakService localKeycloakService() {
        return LocalKeycloakService.localKeycloakService();
    }

} /// :~