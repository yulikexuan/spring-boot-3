//: sfg6lab.domain.service.payment.FraudCheckService.java


package sfg6lab.domain.service.payment;


import sfg6lab.domain.model.payment.PaymentCard;


public interface FraudCheckService {

    void fraudCheck(PaymentCard paymentCard);

}///:~