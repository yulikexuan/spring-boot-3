//: sfg6lab.domain.service.payment.PaymentGatewayService


package sfg6lab.domain.service.payment;


import sfg6lab.domain.model.payment.PaymentCard;


public interface PaymentGatewayService {

    String process(PaymentCard paymentCard);

}///:~