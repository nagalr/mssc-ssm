package guru.springframework.msscssm.repository;

import guru.springframework.msscssm.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ronnen on 29-Jul-2021
 */


public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
