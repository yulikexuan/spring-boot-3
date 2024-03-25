//: sfg6lab.app.Sfg6DbExitCodeGenerator.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Order(1)
@Component
class Sfg6DbExitCodeGenerator implements ExitCodeGenerator {

    @Value("${sfg6.datasource.url:unknown}")
    private String url;

    @Override
    public int getExitCode() {
        log.debug(">>> DB URL is {}", url);
        return this.url.equals("unknown") ? 255 : 0;
    }

}///:~