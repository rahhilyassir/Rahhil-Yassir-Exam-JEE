package yassir.dtos;

import lombok.Data;
import yassir.enums.ContractStatus;

import java.util.Date;

@Data
public class InsuranceContractDTO {
    private String id;
    private String type;
    private Date subscriptionDate;
    private ContractStatus status;
    private Date validationDate;
    private double contributionAmount;
    private int duration;
    private double coverageRate;
    private CustomerDTO customerDTO;
}
