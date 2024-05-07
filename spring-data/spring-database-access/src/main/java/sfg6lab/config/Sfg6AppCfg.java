//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Slf4j
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class, Controller.class, Service.class }),
        basePackages = { "sfg6lab.service", "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(
        basePackages = { "sfg6lab.config", "sfg6lab.domain.model" })
public class Sfg6AppCfg implements AsyncConfigurer {

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:54315/yulikexuan?currentSchema=date4u")
                .username("postgres")
                .password("tecsys")
                .build();
    }

    @Bean("itopiaDataSource")
    public DataSource itopiaDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:54315/tecsys_dev_all?currentSchema=tecsys_dev_all_m")
                .username("postgres")
                .password("tecsys")
                .build();
    }

}///:~
