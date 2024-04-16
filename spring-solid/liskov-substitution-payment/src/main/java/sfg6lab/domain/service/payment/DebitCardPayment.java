//: sfg6lab.domain.service.payment.DebitCardPayment.java


package sfg6lab.domain.service.payment;


import lombok.NonNull;
import sfg6lab.domain.model.payment.PaymentCard;


public class DebitCardPayment extends PaymentInstrument {

    private final DebitCardValidator debitCardValidator;

    public DebitCardPayment(
            @NonNull final PaymentCard paymentCard,
            @NonNull final FraudCheckService fraudCheckService,
            @NonNull final PaymentGatewayService paymentGatewayService,
            @NonNull final DebitCardValidator debitCardValidator) {

        super(paymentCard, fraudCheckService, paymentGatewayService);

        this.debitCardValidator = debitCardValidator;
    }

    @Override
    public void validate() {

        super.validate();

        this.validateDebitCard();
    }

    void validateDebitCard() {
        this.debitCardValidator.validateDebitCard(this.paymentCard);
    }

}///:~