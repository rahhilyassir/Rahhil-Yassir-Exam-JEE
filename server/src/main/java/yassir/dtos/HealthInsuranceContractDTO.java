package yassir.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import yassir.enums.CoverageLevel;

@Data @EqualsAndHashCode(callSuper = true)
public class HealthInsuranceContractDTO extends InsuranceContractDTO {
    private CoverageLevel coverageLevel;
    private int coveredPersons;
}
