//: springlab.config.OrderingConfig.java


package springlab.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springlab.domain.model.CoffeeBrewingType;
import springlab.domain.model.CoffeeOrder;
import springlab.domain.model.Task;


@Configuration
class OrderingConfig {

    @Bean
    @Order(100)
    Task lowPriorityTask() {
        return new Task(100);
    }

    @Bean
    @Order(1)
    Task highPriorityTask() {
        return new Task(1);
    }

    @Bean
    @Order(50)
    Task mediumPriorityTask() {
        return new Task(50);
    }

    // See @Bean Annotation Attribute [init|destroy]Method: page 153
    @Bean(destroyMethod = "")
    CoffeeOrder frenchPressCoffeeOrder() {
        return CoffeeOrder.of(CoffeeBrewingType.POUR_OVER);
    }

    @Bean(destroyMethod = "")
    CoffeeOrder pourOverCoffeeOrder() {
        return CoffeeOrder.of(CoffeeBrewingType.FRENCH_PRESS);
    }

}///:~