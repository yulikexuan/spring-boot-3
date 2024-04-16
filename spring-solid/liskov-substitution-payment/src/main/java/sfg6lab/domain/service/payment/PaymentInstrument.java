//: sfg6lab.domain.service.payment.PaymentInstrument.java


package sfg6lab.domain.service.payment;


import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import sfg6lab.domain.model.payment.PaymentCard;


@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class PaymentInstrument {

    final PaymentCard paymentCard;

    final FraudCheckService fraudCheckService;
    final PaymentGatewayService paymentGatewayService;

    String fingerprint;

    public void validate() {

        Assert.notNull(paymentCard, ">>> Payment card is required");

        final var cardHolderName = paymentCard.name();

        Assert.isTrue(StringUtils.isNotBlank(cardHolderName),
                ">>> Card Holder Name is Blank!");

        Assert.isTrue(StringUtils.isNotEmpty(cardHolderName),
                ">>> Card Holder Name is Empty!");

        Assert.isTrue(paymentCard.isNotExpired(),
                ">>> Payment Card is Expired already!");
    }

    public void runFraudChecks() {
        this.fraudCheckService.fraudCheck(this.paymentCard);
    }

    public void sendToPaymentGateway() {
        this.fingerprint = this.paymentGatewayService.process(paymentCard);
    }

    public String fingerprint() {
        return this.fingerprint;
    }

}///:~