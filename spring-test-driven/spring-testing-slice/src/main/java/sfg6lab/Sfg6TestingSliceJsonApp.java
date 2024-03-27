package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sfg6lab.config.Sfg6ObjectMappingCfg;


@Slf4j
public class Sfg6TestingSliceJsonApp {

    public static void main(String... args) {

        final var ctx = new SpringApplicationBuilder(Sfg6ObjectMappingCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build(args)
                .run(args);

        var beanNames = ctx.getBeanDefinitionNames();
        log.debug(">>> Having {} bean names", beanNames.length);
    }

}