package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by ronnen on 30-Jul-2021
 */

@Slf4j
@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .amount(new BigDecimal("12.99"))
                .build();
    }

    @Transactional
    @Test
    void preAuth() {

        log.debug("Initial 'payment' state: " + payment.getState());

        Payment savedPayment = paymentService.newPayment(payment);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getOne(payment.getId());

        log.debug("sm object state: " + sm.getState().getId().toString());

        log.debug("preAuthPayment is: " + preAuthPayment);
    }

    //    @AfterEach
//    void tearDown() {
//        reset(repository);
//    }

//    @Test
//    void newPayment() {
//
//        // when
//        when(repository.save(any(Payment.class))).thenReturn(payment);
//
//        Payment receivedPayment = paymentService.newPayment(Payment.builder().build());
//
//        String receivedPaymentState = (receivedPayment.getState() != null) ? receivedPayment.getState().toString() : "null";
//
//        // then
//        then(repository).should(times(1)).save(any(Payment.class));
//        assertEquals("NEW", receivedPaymentState);
//    }
}
