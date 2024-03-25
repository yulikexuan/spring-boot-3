//: sfg6lab.config.Author.java


package sfg6lab.config;


import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;


public record Author(
        @DefaultValue
        String username,
        @DefaultValue
        String email,
        int priority,
        String secret,
        @DefaultValue("1000 St-Louis")
        String address,
        @DefaultValue
        Map<String, Phone> phones) {
}///:~