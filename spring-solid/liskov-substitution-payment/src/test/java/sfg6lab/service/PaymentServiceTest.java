//: sfg6lab.service.PaymentServiceTest.java

package sfg6lab.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.AlternativeJdkIdGenerator;
import sfg6lab.domain.model.payment.OrderDetails;
import sfg6lab.domain.model.payment.PaymentCard;
import sfg6lab.domain.service.payment.*;
import sfg6lab.repository.PaymentRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
@DisplayName("Approve Payment System Breaks Liskov Substitution Principle - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private FraudCheckService fraudCheckService;

    @Mock
    private PaymentGatewayService paymentGatewayService;

    @Mock
    private CreditCardValidator creditCardValidator;

    @Mock
    private DebitCardValidator debitCardValidator;

    @Mock
    private RewardCardValidator rewardCardValidator;

    @Mock
    private OrderDetails orderDetails;

    @InjectMocks
    private PaymentService paymentService;

    private String name;
    private String cardNumber;
    private String verificationCode;
    private LocalDateTime expiryDate;
    private String fingerprint;

    @BeforeEach
    void setUp() {

        var idGenerator = new AlternativeJdkIdGenerator();

        this.name = RandomStringUtils.randomAlphabetic(10);
        this.cardNumber = idGenerator.generateId().toString();
        this.verificationCode = idGenerator.generateId().toString();
        this.fingerprint = idGenerator.generateId().toString();
        this.expiryDate = LocalDateTime.now().plusYears(1);
    }

    @Test
    void verify_Liskov_With_Credit_Card_Payment() {

        // Given
        PaymentCard paymentCard = new PaymentCard(
                this.name, this.cardNumber, this.verificationCode, this.expiryDate);

        PaymentInstrument paymentInstrument = new CreditCardPayment(
                paymentCard,
                fraudCheckService,
                paymentGatewayService,
                creditCardValidator);

        given(this.paymentGatewayService.process(paymentCard))
                .willReturn(this.fingerprint);

        // When
        this.paymentService.processPayment(paymentInstrument, orderDetails);

        // Then
        assertThat(paymentInstrument.fingerprint()).isEqualTo(fingerprint);
        then(this.fraudCheckService).should().fraudCheck(paymentCard);
        then(this.paymentRepository).should().save(orderDetails, fingerprint);
        then(this.creditCardValidator).should().validateCreditCard(paymentCard);

        then(this.debitCardValidator).should(never()).validateDebitCard(paymentCard);
    }

    @Test
    void verify_Liskov_With_Debit_Card_Payment() {

        // Given
        PaymentCard paymentCard = new PaymentCard(
                this.name, this.cardNumber, this.verificationCode, this.expiryDate);

        PaymentInstrument paymentInstrument = new DebitCardPayment(
                paymentCard,
                fraudCheckService,
                paymentGatewayService,
                debitCardValidator);

        given(this.paymentGatewayService.process(paymentCard))
                .willReturn(this.fingerprint);

        // When
        this.paymentService.processPayment(paymentInstrument, orderDetails);

        // Then
        assertThat(paymentInstrument.fingerprint()).isEqualTo(fingerprint);
        then(this.fraudCheckService).should().fraudCheck(paymentCard);
        then(this.paymentRepository).should().save(orderDetails, fingerprint);
        then(this.debitCardValidator).should().validateDebitCard(paymentCard);

        then(this.creditCardValidator).should(never()).validateCreditCard(paymentCard);
    }

    @Test
    void reward_Card_Payment_Breaks_Liskov() {

        // Given
        PaymentCard paymentCard = new PaymentCard(
                this.name, this.cardNumber, this.verificationCode, this.expiryDate);

        PaymentInstrument paymentInstrument = new RewardCardPayment(
                paymentCard,
                fraudCheckService,
                paymentGatewayService,
                rewardCardValidator);

        // When
        assertThatThrownBy(() -> this.paymentService.processPayment(
                paymentInstrument, orderDetails))
                .isExactlyInstanceOf(NullPointerException.class);

        // Then
        assertThat(paymentInstrument.fingerprint()).isNull();
        then(this.rewardCardValidator).should().validateRewardCard(paymentCard);
        then(this.fraudCheckService).should(never()).fraudCheck(paymentCard);
        then(this.paymentGatewayService).should(never()).process(paymentCard);
        then(this.debitCardValidator).should(never()).validateDebitCard(paymentCard);
        then(this.creditCardValidator).should(never()).validateCreditCard(paymentCard);
    }

}///:~