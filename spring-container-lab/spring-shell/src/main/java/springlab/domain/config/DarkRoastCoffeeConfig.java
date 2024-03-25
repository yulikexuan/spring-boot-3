//: springlab.domain.config.DarkRoastCoffeeConfig.java


package springlab.domain.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBeanType;
import springlab.domain.model.CoffeeBeans;
import springlab.domain.model.Water;


@Configuration
public class DarkRoastCoffeeConfig {

    @Bean
    Water water() {
        return new Water(97);
    }

    @Bean
    CoffeeBeans coffeeBeans() {
        return new CoffeeBeans(CoffeeBeanType.ARABICA);
    }

    @Bean
    Coffee coffee(Water water, CoffeeBeans coffeeBeans) {
        return new Coffee(water, coffeeBeans);
    }

}///:~