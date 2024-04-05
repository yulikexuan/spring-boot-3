//: sfg6lab.domain.model.SystemPropertyUtilsTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.SystemPropertyUtils;


@Slf4j
@DisplayName("Test SystemPropertyUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SystemPropertyUtilsTest {

    @Test
    void only_Resolve_System_Level_Properties() {

        log.debug(">>> Resolve ${java.runtime.version}: {}",
                SystemPropertyUtils.resolvePlaceholders(
                        "${java.runtime.version}"));
    }

}///:~