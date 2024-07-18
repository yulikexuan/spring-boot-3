//: sfg6lab.domain.model.User.java

package sfg6lab.domain.model;


import lombok.NonNull;


public record User(@NonNull String username,
                   @NonNull Name name,
                   @NonNull Address homeAddress,
                   Address workAddress) {

}

record Name(
        @NonNull String firstName,
        @NonNull String lastName,
        String middleName,
        String title) {
}

record Address(@NonNull String addressLine1,
               String addressLine2,
               @NonNull String city,
               @NonNull String zip) {
}


