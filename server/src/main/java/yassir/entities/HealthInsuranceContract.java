package yassir.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import yassir.enums.CoverageLevel;

@Entity
@DiscriminatorValue("HEALTH")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class HealthInsuranceContract extends InsuranceContract {
    @Enumerated(EnumType.STRING)
    private CoverageLevel coverageLevel;
    private int coveredPersons;
}
