package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sfg6lab.config.SpringEnvCfg;


@Slf4j
public class Sfg6I18nApp {

    public static void main(String[] args) {

        final var ctx = new SpringApplicationBuilder(SpringEnvCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build(args)
                .run(args);
    }

}
