//: sfg6lab.domain.service.payment.CreditCardPayment.java


package sfg6lab.domain.service.payment;


import lombok.NonNull;
import sfg6lab.domain.model.payment.PaymentCard;


public class CreditCardPayment extends PaymentInstrument {

    private final CreditCardValidator creditCardValidator;

    public CreditCardPayment(
            @NonNull final PaymentCard paymentCard,
            @NonNull final FraudCheckService fraudCheckService,
            @NonNull final PaymentGatewayService paymentGatewayService,
            @NonNull final CreditCardValidator creditCardValidator) {

        super(paymentCard, fraudCheckService, paymentGatewayService);
        this.creditCardValidator = creditCardValidator;
    }

    @Override
    public void validate() {

        super.validate();

        this.validateCreditCard();
    }

    void validateCreditCard() {
        this.creditCardValidator.validateCreditCard(this.paymentCard);
    }

}///:~