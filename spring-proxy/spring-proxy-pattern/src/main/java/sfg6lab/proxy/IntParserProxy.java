//: sfg6lab.proxy.IntParserProxy.java


package sfg6lab.proxy;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.TypeUtils;
import sfg6lab.domain.model.IntParser;
import sfg6lab.domain.model.Trim;


@Slf4j
public class IntParserProxy {

    static Object invoke(MethodInvocation invocation) {
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            log.error(">>> Error: {}", e.getMessage());
            return false;
        }
    }

    static void reviseArgument(@NonNull MethodInvocation invocation) {
        invocation.getArguments()[0] = ((String) invocation.getArguments()[0])
                .trim();
    }

    static Object IntParserInterceptor(MethodInvocation invocation) {

        log.debug(">>> Intercepting IntParser Call {}", invocation);

        boolean isProxyMethod = "parseInt".equals(
                invocation.getMethod().getName());

        var method = invocation.getMethod();

        if (invocation.getArguments().length < 2) {
            return Integer.MIN_VALUE;
        }

        boolean isTrimParameter = TypeUtils.isAssignable(
                method.getParameterAnnotations()[0][0].annotationType(),
                Trim.class);

        if (isProxyMethod && isTrimParameter) {
            reviseArgument(invocation);
            return  invoke(invocation);
        } else {
            return Integer.MIN_VALUE;
        }
    }

    static IntParser of(@NonNull IntParser intParser) {

        ProxyFactory pf = new ProxyFactory(intParser);
        MethodInterceptor mi = IntParserProxy::IntParserInterceptor;
        pf.addAdvice(mi);

        return (IntParser) pf.getProxy();
    }

}///:~