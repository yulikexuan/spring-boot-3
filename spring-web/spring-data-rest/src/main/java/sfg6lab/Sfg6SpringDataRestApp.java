package sfg6lab;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sfg6lab.config.Sfg6AppCfg;
import sfg6lab.domain.service.DataInitService;


@Slf4j
public class Sfg6SpringDataRestApp {

    public static void main(String... args) {

        final ConfigurableApplicationContext ctx =
                new SpringApplicationBuilder(Sfg6AppCfg.class)
                        .headless(true)
                        .bannerMode(Banner.Mode.OFF)
                        .logStartupInfo(false)
                        .build(args)
                        .run(args);

        long count = ctx.getBean("dataInitService", DataInitService.class)
                .initialize();

        log.info(">>> {} buyers added to db☺", count);
    }

}
