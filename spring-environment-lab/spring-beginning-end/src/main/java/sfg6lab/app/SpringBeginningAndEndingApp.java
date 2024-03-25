package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.system.ApplicationPid;
import sfg6lab.config.SpringEnvCfg;

import java.util.Arrays;
import java.util.Scanner;


@Slf4j
public class SpringBeginningAndEndingApp {

    public static void main(String[] args) {


        final var app = new SpringApplicationBuilder(SpringEnvCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                // PID file will be deleted automatically after the app ends
                // normally
                .listeners(new ApplicationPidFileWriter())
                .build(args);

        try (var ctx = app.run(args)) {

            log.debug(">>> The current application PID is {}", new ApplicationPid());

            /*
             * When multiple generators are available, the first non-zero exit code
             * is used
             *
             * Generators are ordered based on their Ordered implementation and
             * @Order annotation
             */
            String ecg = Arrays.toString(ctx.getBeanNamesForType(
                    ExitCodeGenerator.class));
            log.debug(">>> ExitCodeGenerator instances: {}", ecg);

            final int exitCode = SpringApplication.exit(ctx);
            log.info(">>> We will exit with code {}", exitCode);

            System.out.println(">>> Please press Enter Key to Continue ... ");
            new Scanner(System.in).nextLine();

            System.exit(exitCode);
        }

    }

}