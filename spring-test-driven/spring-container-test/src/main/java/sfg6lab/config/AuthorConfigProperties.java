//: sfg6lab.config.AuthorConfigProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("sfg6.author")
public record AuthorConfigProperties(String name, String phone) {

}///:~