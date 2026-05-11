package yassir.repositories;

import yassir.entities.InsuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceContractRepository extends JpaRepository<InsuranceContract, String> {
    List<InsuranceContract> findByCustomerId(Long customerId);
}
