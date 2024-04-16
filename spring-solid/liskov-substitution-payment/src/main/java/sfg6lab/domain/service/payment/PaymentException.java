//: sfg6lab.domain.service.payment.PaymentException.java


package sfg6lab.domain.service.payment;


public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

}///:~