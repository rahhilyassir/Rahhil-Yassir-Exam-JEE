package yassir.dtos;

import lombok.Data;

@Data
public class CreateCarContractDTO {
    private double contributionAmount;
    private int duration;
    private double coverageRate;
    private Long customerId;
    private String vehicleRegistrationNumber;
    private String vehicleBrand;
    private String vehicleModel;
}
