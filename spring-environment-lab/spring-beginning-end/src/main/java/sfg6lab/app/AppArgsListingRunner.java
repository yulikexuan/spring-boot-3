//: sfg6lab.config.AppBeginningRunner.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Order(2)
@Component
class AppArgsListingRunner implements ApplicationRunner, ExitCodeGenerator {

    @Override
    public void run(final ApplicationArguments appArgs) throws Exception {

        log.debug(">>> STEP 2. List all arguments of app ------- ");

        // nonoption --option1=value1 --option2=value2 --option3

        log.debug("  >>> Source Args: {}", Arrays.toString(appArgs.getSourceArgs()));

        log.debug("  >>> Non Option Args: {}", appArgs.getNonOptionArgs());

        log.debug("  >>> Option Names: {}", appArgs.getOptionNames());

        appArgs.getOptionNames().forEach(name -> log.debug(
                "    -- Option Value of {}: {}",
                name,
                appArgs.getOptionValues(name)));
    }

    @Override
    public int getExitCode() {
        return 0;
    }

}///:~