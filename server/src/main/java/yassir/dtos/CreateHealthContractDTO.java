package yassir.dtos;

import lombok.Data;
import yassir.enums.CoverageLevel;

@Data
public class CreateHealthContractDTO {
    private double contributionAmount;
    private int duration;
    private double coverageRate;
    private Long customerId;
    private CoverageLevel coverageLevel;
    private int coveredPersons;
}
