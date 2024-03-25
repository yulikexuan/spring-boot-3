//: sfg6lab.domain.model.Profile.java


package sfg6lab.domain.model;


import lombok.Data;

import java.util.UUID;


@Data
public class Profile {

    UUID id;

    String username;

    int secretLevel;

}///:~