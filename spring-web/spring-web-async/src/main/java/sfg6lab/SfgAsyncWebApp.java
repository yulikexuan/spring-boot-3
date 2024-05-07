package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sfg6lab.config.Sfg6AppCfg;

import java.util.Scanner;


@Slf4j
public class SfgAsyncWebApp {

    public static void main(String... args) {

        final var ctx = new SpringApplicationBuilder(Sfg6AppCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build(args)
                .run(args);
    }

}
