//: sfg6lab.service.PaymentService.java


package sfg6lab.service;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sfg6lab.domain.model.payment.OrderDetails;
import sfg6lab.domain.service.payment.PaymentException;
import sfg6lab.domain.service.payment.PaymentInstrument;
import sfg6lab.repository.PaymentRepository;


@Slf4j
@AllArgsConstructor(staticName = "of")
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public void processPayment(
            @NonNull final PaymentInstrument paymentInstrument,
            @NonNull final OrderDetails orderDetails) {

        try {

            paymentInstrument.validate();
            paymentInstrument.runFraudChecks();
            paymentInstrument.sendToPaymentGateway();

            saveToDatabase(orderDetails, paymentInstrument.fingerprint());

        } catch (PaymentException e) {
            log.error(e.getMessage());
        }
    }

    private void saveToDatabase(
            @NonNull final OrderDetails orderDetails,
            @NonNull final String fingerprint) {

        this.paymentRepository.save(orderDetails, fingerprint);
    }

}///:~