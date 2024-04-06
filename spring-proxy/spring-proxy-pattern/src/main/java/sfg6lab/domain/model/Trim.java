package sfg6lab.domain.model;


import java.lang.annotation.*;


@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trim {
}