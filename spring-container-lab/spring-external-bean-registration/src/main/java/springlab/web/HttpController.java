//: springlab.web.HttpController.java


package springlab.web;


import org.springframework.stereotype.Controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Controller
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpController {

    String value() default "/";

}///:~