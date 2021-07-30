package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ronnen on 29-Jul-2021
 */

@SpringBootTest
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNewStateMachine() {

        // 'getStateMachine' - Build a new StateMachine instance with a given machine uuid, returns it.
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID().toString());
        sm.start();

        // the initial state is 'NEW'
        System.out.println("Initial State: " + sm.getState().getId().toString());

        // send an event to trigger a state change. ('PRE_AUTHORIZE' event does not change state)
        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println("After First State Change: " + sm.getState().getId().toString());

        // send an event to trigger a state change.
        sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        System.out.println("After Second State Change: " + sm.getState().getId().toString());

        assertEquals("PRE_AUTH", sm.getState().getId().toString());
   }
}
