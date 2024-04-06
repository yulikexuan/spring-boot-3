package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sfg6lab.config.Sfg6AppCfg;


@Slf4j
public class Sfg6ProxyApp {

    public static void main(String... args) {

        final var ctx = new SpringApplicationBuilder(Sfg6AppCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build(args)
                .run(args);

//        System.out.println(">>> Press Enter Key to Continue ... ");
//        new Scanner(System.in).nextLine();
//        System.exit(SpringApplication.exit(ctx));
    }

}
