//: springlab.service.RenderService.java


package springlab.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


/*
 * If the evaluation context has been configured with a bean resolver
 * it is possible to lookup beans from an expression using the (@) symbol
 */
@Component
@DependsOn("coffeeService")
@ConditionalOnExpression("@coffeeService != null ")
public class RenderingService {

}///:~