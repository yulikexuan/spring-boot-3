//: springlab.config.LocationRegistration.java


package springlab.config;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import springlab.domain.model.Location;


public class LocationRegistration {

    LocationRegistration(DefaultListableBeanFactory beanFactory) {

        final var beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(Location.class)
                .addPropertyValue("longitude", 45.499551)
                .addPropertyValue("latitude", -73.696901)
                .getBeanDefinition();

        beanFactory.registerBeanDefinition("location", beanDefinition);
    }

}///:~