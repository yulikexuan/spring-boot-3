package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;
import sfg6lab.config.SpringEnvCfg;
import sfg6lab.service.ClassLoadingService;
import sfg6lab.service.ClassResourceCollectionCheck;
import sfg6lab.service.ResourceCheckingService;
import sfg6lab.service.ResourceCollectionCheck;

import java.util.Scanner;


@Slf4j
public class SpringResourcesApp {

    static final String CLASSPATH_AUTHOR_JSON = "classpath:author.json";

    public static void main(String[] args) throws Exception {

        final var ctx = new SpringApplicationBuilder(SpringEnvCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                // PID file will be deleted automatically after the app ends
                // normally
                .listeners(new ApplicationPidFileWriter())
                .build(args)
                .run(args);

        accessResource(ctx);

        ctx.getBean(ResourceCollectionCheck.class).check();
        ctx.getBean(ClassResourceCollectionCheck.class).check();
        ctx.getBean(ClassLoadingService.class).check();

        System.out.println(">>> Press Enter Key to Continue ... ");
        new Scanner(System.in).nextLine();

        System.exit(SpringApplication.exit(ctx));
    }

    static void accessResource(@NonNull ConfigurableApplicationContext ctx) {

        for (ResourceCheckingService checkingService :
                ctx.getBeansOfType(ResourceCheckingService.class).values()) {

            log.debug(">>> The length is {}", checkingService.check());
        }

    }

}