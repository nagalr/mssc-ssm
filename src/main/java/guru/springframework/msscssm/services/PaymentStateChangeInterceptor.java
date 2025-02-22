package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by ronnen on 01-Aug-2021
 */

@Component
@RequiredArgsConstructor
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {

    private final PaymentRepository paymentRepository;

    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state,
                               Message<PaymentEvent> message,
                               Transition<PaymentState, PaymentEvent> transition,
                               StateMachine<PaymentState, PaymentEvent> stateMachine) {

        // code to persist State Changes
        Optional.ofNullable(message).ifPresent(msg -> {
           Optional.ofNullable((Long) msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1L))
                   .ifPresent(paymentId -> {
                      Payment payment = paymentRepository.getOne(paymentId);
                      payment.setState(state.getId());
                      paymentRepository.save(payment);
                   });
        });
    }
}
