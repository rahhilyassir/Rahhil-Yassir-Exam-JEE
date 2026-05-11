package yassir.dtos;

import lombok.Data;
import yassir.enums.PaymentType;

@Data
public class PaymentRequestDTO {
    private String contractId;
    private double amount;
    private PaymentType type;
    private String description;
}
