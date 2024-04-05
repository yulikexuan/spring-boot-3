//: app.aspect.ToLoggingAnnotationAspect.java


package app.aspect;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;


@Slf4j
@Aspect
@NoArgsConstructor(staticName = "of")
public class ToLoggingAnnotationAspect {

    @Around("@annotation(ToLogging)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(">>> Running Aspect of Methods with Annotation ToLogging");

        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();

        for (Object arg : arguments) {
            log.info(">>> [AOP] Will execute @ToLogging method {} with arguments {}",
                    methodName, List.of(arguments));
        }

        Object returnedByMethod = joinPoint.proceed();

        log.info(">>> Method executed and returned {}", returnedByMethod);

        return returnedByMethod;
    }

}///:~