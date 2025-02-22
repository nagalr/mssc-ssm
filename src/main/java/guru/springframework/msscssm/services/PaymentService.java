package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

/**
 * Created by ronnen on 30-Jul-2021
 */


public interface PaymentService {

    Payment newPayment(Payment payment);
    StateMachine<PaymentState, PaymentEvent> preAuth (Long paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment (Long paymentId);
    StateMachine<PaymentState, PaymentEvent> declineAuth (Long paymentId);
}
