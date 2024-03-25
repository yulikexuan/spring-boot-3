//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sfg6lab.domain.model.DbConnectionFactory;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class, Service.class, ShellComponent.class}),
        basePackages = { "sfg6lab.service", "sfg6lab.shell.commands"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class SpringEnvCfg {

    @Autowired
    private Sfg6ServerConfigurationProperties sfg6ServerConfigurationProperties;

    private static final PromptProvider DEFAULT_PROMPT_PROVIDER =
            () -> new AttributedString("profile:> ",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

    @Bean
    PromptProvider promptProvider() {
        return DEFAULT_PROMPT_PROVIDER;
    }

    @Bean
    @Profile("!dev & !qa") // The "default" profile
    DbConnectionFactory productDbConnectionFactory() {
        return DbConnectionFactory.productDbConnectionFactory(
                sfg6ServerConfigurationProperties.dbSource());
    }

    @Bean
    @Profile("dev")
    DbConnectionFactory devDbConnectionFactory() {
        return DbConnectionFactory.devDbConnectionFactory(
                sfg6ServerConfigurationProperties.dbSource());
    }

    @Bean
    @Profile("qa")
    DbConnectionFactory qaDbConnectionFactory() {
        return DbConnectionFactory.qaDbConnectionFactory(
                sfg6ServerConfigurationProperties.dbSource());
    }

}///:~
