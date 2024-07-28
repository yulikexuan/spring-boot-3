//: sfg6lab.domain.model.Mail.java

package sfg6lab.domain.model;


import lombok.NonNull;


public record Mail(@NonNull String street, @NonNull String city) {

    public static Mail of(@NonNull String street, @NonNull String city) {
        return new Mail(street, city);
    }
}
