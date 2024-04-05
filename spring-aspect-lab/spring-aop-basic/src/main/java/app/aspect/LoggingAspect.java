//: app.aspect.LoggingAspect.java


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
public class LoggingAspect {

    @Around("execution(* app.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            log.info(">>> Will execute method {} with arguments {}",
                    methodName, List.of(args));
        }

        Object result = null;
        if (shouldProceed(joinPoint)) {
            result = joinPoint.proceed();
        }

        log.info(">>> After executing a service call {}", methodName);

        return result;
    }

    boolean shouldProceed(ProceedingJoinPoint joinPoint) {
        return true;
    }

}///:~