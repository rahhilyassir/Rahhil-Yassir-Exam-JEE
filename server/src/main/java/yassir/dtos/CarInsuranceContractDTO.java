package yassir.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class CarInsuranceContractDTO extends InsuranceContractDTO {
    private String vehicleRegistrationNumber;
    private String vehicleBrand;
    private String vehicleModel;
}
