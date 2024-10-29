//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sfg6lab.controller.data.BuyerDto;
import sfg6lab.controller.data.BuyerMapper;
import sfg6lab.controller.data.DataMaper;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.model.YearMonthRange;
import sfg6lab.domain.service.DataInitService;
import sfg6lab.servlet.GreetingServlet;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;


@Slf4j
@EnableAutoConfiguration
@SpringBootConfiguration
// ServletComponentScan MUST be used with @WebServlet
@ServletComponentScan(basePackages = {"sfg6lab.servlet"})
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Controller.class,
                Service.class,
                Repository.class}),
        basePackages = { "sfg6lab.service",
                "sfg6lab.domain.model",
                "sfg6lab.domain.repository",
                "sfg6lab.domain.service",
                "sfg6lab.service",
                "sfg6lab.controller"})
@EntityScan(basePackages = { "sfg6lab.domain.model" })
@EnableJdbcRepositories(basePackages = { "sfg6lab.domain.repository" })
public class Sfg6AppCfg {

    @Value("${sfg6.web.url-mapping.greeting}")
    private String greetingUrl;

    @Value("${sfg6.app.icon-path}")
    private String iconPath;

    @Bean
    // Do not require @ServletComponentScan
    ServletRegistrationBean greetingServlet() {
        return new ServletRegistrationBean(
                new GreetingServlet(), greetingUrl);
    }

    @Bean
    Resource appIconResource() throws IOException {
        return new ClassPathResource(iconPath);
    }

    /*
     * When the Spring Boot application starts up, it takes all beans that
     * implement the Formatter interface and automatically registers them.
     */
    @Bean
    Formatter<YearMonthRange> yearMonthRangeFormatter() {
        return new YearMonthRangeFormatter();
    }

    @Bean
    DataMaper<Buyer, BuyerDto> buyerMapper() {
        return new BuyerMapper();
    }

    @Bean
    Supplier<List<Buyer>> buyerSamplesSupplier() {
        return () -> DataInitService.BUYERS;
    }

}///:~
