//: sfg6lab.domain.model.Author.java


package sfg6lab.domain.model;


import java.util.Map;


public record Author(
        String email,
        int priority,
        String secret,
        String address,
        Map<String, Phone> phones) {

}///:~