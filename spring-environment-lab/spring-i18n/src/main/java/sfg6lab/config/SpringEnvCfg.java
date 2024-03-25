//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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

    private static final PromptProvider DEFAULT_PROMPT_PROVIDER =
            () -> new AttributedString("profile:> ",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

    @Bean
    PromptProvider promptProvider() {
        return DEFAULT_PROMPT_PROVIDER;
    }

    @Bean
    static MessageSourceAccessor messageSourceAccessor(
            @NonNull final MessageSource messageSource) {

        return new MessageSourceAccessor(messageSource);
    }

}///:~