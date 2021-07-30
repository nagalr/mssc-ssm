package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by ronnen on 30-Jul-2021
 */

@WebMvcTest
class PaymentServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .state(PaymentState.NEW)
                .id(1L)
                .build();
    }

    @Test
    void newPayment() {

        // when
        when(repository.save(any(Payment.class))).thenReturn(payment);

        Payment receivedPayment = paymentService.newPayment(Payment.builder().build());

        String receivedPaymentState = (receivedPayment.getState() != null) ? receivedPayment.getState().toString() : "null";

        // then
        then(repository).should(times(1)).save(any(Payment.class));
        assertEquals("NEW", receivedPaymentState);
    }
}
