//: sfg6lab.proxy.ListProxy.java


package sfg6lab.proxy;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
public class ListProxy {

    static <T> List<T> of(List<T> list) {

        ProxyFactory pf = new ProxyFactory(list);

        MethodInterceptor mi = ListProxy::addToListInterceptor;
        pf.addAdvice(mi);

        return (List<T>) pf.getProxy();
    }

    static boolean addToListInterceptor(MethodInvocation invocation) {

        log.debug(">>> Intercepting List::add Call {}", invocation);

        boolean callingAdd = "add".equals(invocation.getMethod().getName());
        boolean argIsNull = isNull(invocation.getArguments()[0]);

        if (callingAdd && argIsNull) {
            log.debug(">>> Intercepting List::add Call with null argument");
            return false;
        } else {
            return (Boolean) invoke(invocation);
        }
    }

    static boolean invoke(MethodInvocation invocation) {
        try {
            return (Boolean) invocation.proceed();
        } catch (Throwable e) {
            log.error(">>> Error: {}", e.getMessage());
            return false;
        }
    }

}///:~