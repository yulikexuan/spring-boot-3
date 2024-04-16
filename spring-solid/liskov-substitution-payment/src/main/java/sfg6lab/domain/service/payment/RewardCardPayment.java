//: sfg6lab.domain.service.payment.RewardCardPayment.java


package sfg6lab.domain.service.payment;


import lombok.NonNull;
import sfg6lab.domain.model.payment.PaymentCard;


public class RewardCardPayment extends PaymentInstrument {

    private final RewardCardValidator rewardCardValidator;

    public RewardCardPayment(
            @NonNull final PaymentCard paymentCard,
            @NonNull final FraudCheckService fraudCheckService,
            @NonNull final PaymentGatewayService paymentGatewayService,
            @NonNull final RewardCardValidator rewardCardValidator) {

        super(paymentCard, fraudCheckService, paymentGatewayService);

        this.rewardCardValidator = rewardCardValidator;
    }

    @Override
    public void validate() {

        super.validate();

        this.rewardCardValidator.validateRewardCard(paymentCard);
    }

    @Override
    public void runFraudChecks() {
    }

    @Override
    public void sendToPaymentGateway() {
    }

}///:~