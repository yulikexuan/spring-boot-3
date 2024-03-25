package springlab.service;


import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ TYPE, CONSTRUCTOR, PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CryptographicallyStrong {

}
