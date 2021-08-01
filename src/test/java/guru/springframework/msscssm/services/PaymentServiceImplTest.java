package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by ronnen on 30-Jul-2021
 */

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

        Payment savedPayment = paymentService.newPayment(payment);
        paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getOne(payment.getId());

        System.out.println(preAuthPayment);
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
