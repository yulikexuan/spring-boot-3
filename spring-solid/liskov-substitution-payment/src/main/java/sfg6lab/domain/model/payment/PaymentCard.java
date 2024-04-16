//: sfg6lab.domain.model.payment.Card.java


package sfg6lab.domain.model.payment;


import java.time.LocalDateTime;


public record PaymentCard(
        String name,
        String cardNumber,
        String verificationCode,
        LocalDateTime expiryDate) {

    public boolean isNotExpired() {
        return LocalDateTime.now().isBefore(expiryDate);
    }

}///:~