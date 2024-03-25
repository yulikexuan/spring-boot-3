//: springlab.HierarchicalContextsApp.java


package springlab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springlab.domain.model.Parent;


@Slf4j
@SpringBootConfiguration
@EnableAutoConfiguration
class HierarchicalContextsApp {

    /*
     * proxyBeanMethods = false :
     * Specify whether @Bean methods should get proxied in order to enforce
     * bean lifecycle behavior
     */
    @Configuration(proxyBeanMethods = false)
    @ComponentScan(basePackages = { "springlab.domain" })
    static class DomainConfig {
    }


    @Configuration(proxyBeanMethods = false)
    @ComponentScan(basePackages = { "springlab.web" })
    static class WebConfig {
    }

    public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder()
                .parent(DomainConfig.class)
                .child(WebConfig.class)
                .build(args)
                .run();

        log.info(">>> The Parent Bean: {}", ctx.getBean(Parent.class).toString());
    }

}///:~