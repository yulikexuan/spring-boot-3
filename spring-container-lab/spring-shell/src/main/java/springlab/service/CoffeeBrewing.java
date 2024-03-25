package springlab.service;


import org.springframework.beans.factory.annotation.Qualifier;
import springlab.domain.model.CoffeeBrewingType;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Qualifier
@Target({
        FIELD,
        METHOD,
        TYPE,
        PARAMETER,
        ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CoffeeBrewing {
    CoffeeBrewingType value();
}
