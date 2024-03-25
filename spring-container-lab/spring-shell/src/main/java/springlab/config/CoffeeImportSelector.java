//: springlab.config.CoffeeImportSelector.java


package springlab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import springlab.domain.config.DarkRoastCoffeeConfig;
import springlab.domain.config.MediumRoastCoffeeConfig;

import java.util.concurrent.ThreadLocalRandom;


@Slf4j
class CoffeeImportSelector implements ImportSelector {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public @NonNull String[] selectImports(AnnotationMetadata __) {

        String logmsg = "";
        String[] configs = null;

        if (RANDOM.nextBoolean()) {
            logmsg = "DARK ROAST COFFEE";
            configs = new String[] { DarkRoastCoffeeConfig.class.getName() };
        } else {
            logmsg = "MEDIUM ROAST COFFEE";
            configs = new String[] { MediumRoastCoffeeConfig.class.getName() };
        }

        log.info(">>> Will serve {}", logmsg);
        return configs;
    }

}///:~