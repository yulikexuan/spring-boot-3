//: sfg6lab.config.ProfileConfigProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import sfg6lab.domain.model.Profile;


@ConfigurationProperties(prefix = "sfg6")
public record ProfileConfigProperties(Profile profile) {

}///:~