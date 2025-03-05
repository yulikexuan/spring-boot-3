package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cglib.core.Local;
import sfg6lab.config.SpringEnvCfg;
import sfg6lab.domain.service.LocalKeycloakService;


@Slf4j
public class Sfg6TestDrivenContainerApp {

    public static void main(String... args) {
        final var ctx =
                new SpringApplicationBuilder(SpringEnvCfg.class)
                        .headless(true)
                        .bannerMode(Banner.Mode.OFF)
                        .logStartupInfo(false)
                        .build(args)
                        .run(args);

        boolean containsBean = ctx.containsBean("localKeycloakService");
        System.out.println(">>> Is LocalKeycloakService Bean available? %b"
                .formatted(containsBean));
    }

}