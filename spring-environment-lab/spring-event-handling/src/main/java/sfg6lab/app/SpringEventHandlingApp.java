package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.PayloadApplicationEvent;
import sfg6lab.config.Sfg6ConfigurationProperties;
import sfg6lab.config.SpringEnvCfg;

import java.time.Duration;
import java.util.Scanner;


@Slf4j
public class SpringEventHandlingApp {

    public static void main(String[] args) {

        final var ctx = new SpringApplicationBuilder(SpringEnvCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                // PID file will be deleted automatically after the app ends
                // normally
                .listeners(new ApplicationPidFileWriter())
                .build(args)
                .run(args);

        ctx.start();

        var sfg6Config = ctx.getBean(Sfg6ConfigurationProperties.class);

        ctx.publishEvent(new PayloadApplicationEvent<String>(
                SpringEventHandlingApp.class, sfg6Config.secret()));

        ctx.publishEvent(new PayloadApplicationEvent<Duration>(
                SpringEventHandlingApp.class, sfg6Config.timeout()));

        System.out.println(">>> Press Enter Key to Continue ... ");
        new Scanner(System.in).nextLine();

        System.exit(SpringApplication.exit(ctx));
    }

}