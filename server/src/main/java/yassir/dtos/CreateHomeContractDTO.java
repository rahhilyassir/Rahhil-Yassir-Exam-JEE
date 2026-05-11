package yassir.dtos;

import lombok.Data;
import yassir.enums.HousingType;

@Data
public class CreateHomeContractDTO {
    private double contributionAmount;
    private int duration;
    private double coverageRate;
    private Long customerId;
    private HousingType housingType;
    private String housingAddress;
    private double surface;
}
