//: sfg6lab.config.Sfg6EmailConfigProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@EnableConfigurationProperties
@ConfigurationProperties("sfg6.contact")
// By default, @PropertySource does not load YAML files
// Should setup YamlPropertySourceFactory.class
@PropertySource(value = "classpath:email.yml",
        factory = YamlPropertySourceFactory.class)
public record Sfg6EmailConfigProperties(Email email) {

}///:~