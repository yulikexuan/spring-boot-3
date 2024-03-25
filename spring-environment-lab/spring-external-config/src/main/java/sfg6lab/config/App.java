//: sfg6lab.config.App.java


package sfg6lab.config;


import org.springframework.boot.context.properties.bind.DefaultValue;


public record App(
        @DefaultValue
        String javaVersion,
        @DefaultValue
        String sourceEncoding,
        @DefaultValue
        String outputEncoding,
        @DefaultValue
        String version) {

}///:~