//: sfg6lab.domain.service.payment.CreditCardValidator


package sfg6lab.domain.service.payment;


import sfg6lab.domain.model.payment.PaymentCard;


public interface CreditCardValidator {

    void validateCreditCard(PaymentCard paymentCard);

}///:~