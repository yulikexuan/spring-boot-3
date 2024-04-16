//: sfg6lab.repository.PaymentRepository


package sfg6lab.repository;


import lombok.NonNull;
import sfg6lab.domain.model.payment.OrderDetails;


public interface PaymentRepository {

    void save(@NonNull OrderDetails orderDetails, @NonNull String fingerprint);

}///:~