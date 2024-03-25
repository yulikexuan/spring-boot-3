//: sfg6lab.app.ExitingCodeGenerator.java


package sfg6lab.app;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(2)
@Component
class Sfg6ExitCodeGenerator implements ExitCodeGenerator {

    @Value("${sfg6.exit-code}")
    private int exitCode;

    @Override
    public int getExitCode() {
        return exitCode;
    }

}///:~