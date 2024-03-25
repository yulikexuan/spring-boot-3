//: springlab.domain.config.MediumRoastCoffeeConfig.java


package springlab.domain.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBeanType;
import springlab.domain.model.CoffeeBeans;
import springlab.domain.model.Water;


@Configuration
public class MediumRoastCoffeeConfig {

    @Bean
    Water water() {
        return new Water(92);
    }

    @Bean
    CoffeeBeans coffeeBeans() {
        return new CoffeeBeans(CoffeeBeanType.COLOMBIAN);
    }

    @Bean
    Coffee coffee(Water water, CoffeeBeans coffeeBeans) {
        return new Coffee(water, coffeeBeans);
    }

}///:~