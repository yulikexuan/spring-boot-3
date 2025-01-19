//: app.aspect.CommentAspect.java

package app.aspect;


import app.domain.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class AuthorizatingAspect {

    /*
     * Advice Example:
     *   @Around("execution(* app.service.CommentTransactionService.*(..))")
     *
     * In the Aspect Pointcut Language Expression
     *   1. execution: means "when the intercepted method is called ... "
     *   2. the first *: the intercepted method may have any returned type
     *   3. app.service: means the intercepted method must be in app.service
     *      package
     *   4. CommentService: means the intercepted method must be in this class
     *      if want to intercepted any method in a package, use "*" instead of
     *      a class name
     *   5. the second *: means the intercepted method can have any name, or
     *      all the methods are intercepted
     *   6. (..): means the intercepted method can have any parameters
     *
     * See also: https://docs.spring.io/spring-framework/reference/core/aop/ataspectj.html
     */
    @Before("@annotation(CommentTransaction)")
    // If using @Before advice annotation, the parameter must be JoinPoint
    // @Around("execution(* app.service.CommentTransactionService.*(..))")
    // If using @Around advice annotation, the parameter must be ProceedingJoinPoint
    public void logComment(JoinPoint joinPoint) {

        String interceptedMethodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        Comment comment = (Comment) args[0];
        if (comment.author().length() < 4) {
            throw new AuthorizationFailedException(
                    ">>> Author has no permission to add comment.");
        }

        log.info("- [AOP] Will execute method {} with arguments {}",
                interceptedMethodName, Arrays.toString(args));

//        Object obj = joinPoint.proceed();
//        log.info("- [AOP] Method of CommentService executed. ");
//        return obj;
    }

} /// :~