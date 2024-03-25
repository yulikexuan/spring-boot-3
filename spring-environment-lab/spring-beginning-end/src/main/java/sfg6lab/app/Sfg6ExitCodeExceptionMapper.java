//: sfg6lab.app.Sfg6ExceptionExitCodeMapper.java


package sfg6lab.app;


import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.stereotype.Component;
import sfg6lab.service.InvalidDbConnectionUrlException;


@Component
class Sfg6ExitCodeExceptionMapper implements ExitCodeExceptionMapper {

    @Override
    public int getExitCode(Throwable exception) {

        Throwable cause = exception.getCause();

        return ((exception instanceof InvalidDbConnectionUrlException) ||
                (cause instanceof InvalidDbConnectionUrlException)) ? 127 : 0;
    }

}///:~