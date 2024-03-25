//: sfg6lab.app.UserHomeValidationRunner.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Order(1)
@Component
class UserHomeValidationRunner implements ApplicationRunner {

    @Value("${user.home}")
    private String userHome;

    @Override
    public void run(ApplicationArguments __) throws Exception {

        log.debug(">>> STEP 1. The user.home is {}", this.userHome);

        // To Test Sfg6ExitCodeExceptionMapper
        // throw new InvalidDbConnectionUrlException(">>> Testing DB");
    }

}///:~