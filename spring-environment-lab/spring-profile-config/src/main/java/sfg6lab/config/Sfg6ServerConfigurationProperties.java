//: sfg6lab.config.Cfg6ServerConfigurationProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import sfg6lab.domain.model.DbSource;
import sfg6lab.domain.model.Logger;
import sfg6lab.domain.model.Server;


@ConfigurationProperties("sfg6")
public record Sfg6ServerConfigurationProperties(
        Server server,
        DbSource dbSource,
        Logger logger) {

}///:~