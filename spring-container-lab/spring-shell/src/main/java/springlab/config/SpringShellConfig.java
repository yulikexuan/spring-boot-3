//: springlab.config.SpringShellConfig.java


package springlab.config;


import lombok.extern.slf4j.Slf4j;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.stereotype.Service;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBrewingType;
import springlab.domain.service.CoffeeBrewService;
import springlab.service.CoffeeBrewing;
import springlab.service.CryptographicallyStrong;
import springlab.service.FileSystemService;

import java.lang.reflect.AnnotatedElement;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static springlab.domain.model.CoffeeBrewingType.FRENCH_PRESS;
import static springlab.domain.model.CoffeeBrewingType.POUR_OVER;


@Slf4j
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {
                "springlab.service",
                "springlab.commands",
                "springlab.domain.service"},
        includeFilters = @ComponentScan.Filter({
                Service.class, ShellComponent.class}))
@Import( { CoffeeImportSelector.class,
        OrderingConfig.class,
        ThingProfile.class,
        ProfileUuidConfig.class} )
public class SpringShellConfig {

    @Value("${spring.lab.shell.isAdmin}")
    private boolean isAdmin;

    @Value("${spring.lab.systemUserHomePropertyName}")
    private String systemUserHomePropertyName;

    private static final PromptProvider DEFAULT_PROMPT_PROVIDER =
            () -> new AttributedString("spring-lab:> ",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

    private static final PromptProvider ADMIN_PROMPT_PROVIDER =
            () -> new AttributedString("spring-lab[admin]:> ",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    @Bean
    Path root() {
        return FileSystemService.root(systemUserHomePropertyName);
    }

    @Bean
    PromptProvider promptProvider() {
        return isAdmin ? ADMIN_PROMPT_PROVIDER : DEFAULT_PROMPT_PROVIDER;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    Random random(InjectionPoint injectPoint) {

        final var clazz = CryptographicallyStrong.class;

        // injectPoint.getAnnotation(clazz):
        //   - get the annotation of the constructor parameter

        // injectPoint.getMember()
        //   - Get the constructor, containing the injection point

        if ((injectPoint.getAnnotation(clazz) != null) ||
                (injectPoint.getMember() instanceof AnnotatedElement member &&
                        member.isAnnotationPresent(clazz))) {

            log.info(">>> Will use Secure Random.");
            return new SecureRandom();
        }

        log.info(">>> Will use Thread Local Random.");
        return ThreadLocalRandom.current();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    Supplier<Coffee> coffeeSupplier(Coffee coffee) {
        return () -> coffee;
    }

    @Bean
    Map<CoffeeBrewingType, CoffeeBrewService> brewingServices(
            @CoffeeBrewing(FRENCH_PRESS) CoffeeBrewService frenchPress,
            @CoffeeBrewing(POUR_OVER) CoffeeBrewService pourOver) {

        return Map.of(FRENCH_PRESS, frenchPress, POUR_OVER, pourOver);
    }

    @Bean
    LocationRegistration locationRegistration(
            DefaultListableBeanFactory beanFactory) {

        return new LocationRegistration(beanFactory);
    }

    @Bean
    ExpressionParser expressionParser() {
        return new SpelExpressionParser();
    }

}///:~