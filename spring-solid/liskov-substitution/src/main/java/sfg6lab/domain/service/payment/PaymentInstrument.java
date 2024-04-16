//: sfg6lab.domain.service.payment.PaymentInstrument.java


package sfg6lab.domain.service.payment;


import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;


public abstract class PaymentInstrument {

    String name;
    String cardNumber;
    String verificationCode;
    LocalDateTime expiryDate;
    String fingerprint;

    FraudCheckService fraudCheckService;

    void validate() {

        Assert.isTrue(StringUtils.isNotBlank(name), ">>> Name is required");
        Assert.isTrue(StringUtils.isNotEmpty(name), ">>> Name is required");

        // other validations
    }

    void runFraudChecks() {
        this.fraudCheckService.fraudCheck(cardNumber);
    }

    void sendToPaymentGateway() {
        // send details to payment gateway (PG) and set fingerprint from
        // the payment gateway response
    }

}///:~