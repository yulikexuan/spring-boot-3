//: sfg6lab.config.Sfg6ConfigProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("sfg6")
public record Sfg6ConfigProperties(App app, Server server, Author author) {

}///:~