//: sfg6lab.config.FileSystemConfigProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import sfg6lab.domain.model.FileSystem;


@ConfigurationProperties("sfg6")
public record FileSystemConfigProperties(FileSystem fileSystem) {

}///:~