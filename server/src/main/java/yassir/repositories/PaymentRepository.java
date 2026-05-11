package yassir.repositories;

import yassir.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByContractId(String contractId);
    Page<Payment> findByContractId(String contractId, Pageable pageable);
}
